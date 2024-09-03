package com.virtualworld.scorganizadores.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.common.ScreenStateProducts
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import com.virtualworld.scorganizadores.domain.usecase.category.CategoryUseCase
import com.virtualworld.scorganizadores.domain.usecase.product.GetAllProductsUseCase
import com.virtualworld.scorganizadores.ui.uiData.ProductUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val mapper: ProductListMapper<ProductEntity, ProductUiData>,
) :
    ViewModel() {


    private val _products = MutableLiveData<ScreenStateProducts<List<ProductUiData>>>()
    val products: LiveData<ScreenStateProducts<List<ProductUiData>>> get() = _products

    private val _categories = MutableLiveData<ScreenStateProducts<List<String>>>()
    val categories: LiveData<ScreenStateProducts<List<String>>> get() = _categories

    init {
        getAllCategory()
        getAllProducts()
    }

    private fun getAllProducts() {
        getAllProductsUseCase().onEach {



            when (it) {
                is NetworkResponseState.Error -> _products.postValue(ScreenStateProducts.Error(it.exception.message!!))
                is NetworkResponseState.Loading -> _products.postValue(ScreenStateProducts.Loading)
                is NetworkResponseState.Success -> {_products.postValue(ScreenStateProducts.Success(mapper.map(it.result)))


                }


            }



        }.launchIn(viewModelScope)
    }



    private fun getAllCategory() {
        categoryUseCase().onEach {
            when (it) {
                is NetworkResponseState.Error -> _categories.postValue(ScreenStateProducts.Error(it.exception.message!!))
                is NetworkResponseState.Loading -> _categories.postValue(ScreenStateProducts.Loading)
                is NetworkResponseState.Success ->
                {
                    _categories.postValue(ScreenStateProducts.Success(it.result))

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProductsByCategory(categoryName: String) {
        getAllProductsUseCase(categoryName).onEach {
            when (it) {
                is NetworkResponseState.Error -> _products.postValue(ScreenStateProducts.Error(it.exception.message!!))
                is NetworkResponseState.Loading -> _products.postValue(ScreenStateProducts.Loading)
                is NetworkResponseState.Success -> _products.postValue(ScreenStateProducts.Success(mapper.map(it.result)))
            }
        }.launchIn(viewModelScope)
    }
}
