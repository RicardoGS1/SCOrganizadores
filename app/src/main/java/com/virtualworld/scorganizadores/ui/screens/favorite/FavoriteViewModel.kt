package com.virtualworld.scorganizadores.ui.screens.favorite

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.common.ScreenStateProducts
import com.virtualworld.scorganizadores.domain.entity.product.FavoriteProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductBaseMapper
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import com.virtualworld.scorganizadores.domain.usecase.favorite.DeleteFavoriteUseCase
import com.virtualworld.scorganizadores.domain.usecase.favorite.FavoriteUseCase
import com.virtualworld.scorganizadores.ui.uiData.FavoriteUiData
import com.virtualworld.scorganizadores.common.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
    private val mapper: ProductListMapper<FavoriteProductEntity, FavoriteUiData>,
    private val singleMapper: ProductBaseMapper<FavoriteUiData, FavoriteProductEntity>,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    private val _favoriteCarts = MutableLiveData<ScreenStateProducts<List<FavoriteUiData>>>()
    val favoriteCarts: LiveData<ScreenStateProducts<List<FavoriteUiData>>> get() = _favoriteCarts

    init {
        getFavoriteProducts()
    }

    private fun getFavoriteProducts() {
        viewModelScope.launch {
            favoriteUseCase.invoke(getUserIdFromSharedPref(sharedPreferences)).collect {
                when (it) {
                    is NetworkResponseState.Error -> _favoriteCarts.postValue(
                        ScreenStateProducts.Error(
                            it.exception.message!!,
                        ),
                    )

                    is NetworkResponseState.Loading -> _favoriteCarts.postValue(ScreenStateProducts.Loading)
                    is NetworkResponseState.Success -> _favoriteCarts.postValue(
                        ScreenStateProducts.Success(
                            mapper.map(it.result),
                        ),
                    )
                }
            }
        }
    }

    fun deleteFavoriteItem(favoriteUiData: FavoriteUiData) {
        viewModelScope.launch {
            deleteFavoriteUseCase(singleMapper.map(favoriteUiData))
            getFavoriteProducts()
        }
    }
}
