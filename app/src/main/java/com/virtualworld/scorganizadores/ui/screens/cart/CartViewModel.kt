package com.virtualworld.scorganizadores.ui.screens.cart

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.common.ScreenStateProducts
import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import com.virtualworld.scorganizadores.domain.mapper.ProductBaseMapper
import com.virtualworld.scorganizadores.domain.mapper.ProductListMapper
import com.virtualworld.scorganizadores.domain.usecase.cart.CartUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.DeleteUserCartUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.UpdateCartUseCase
import com.virtualworld.scorganizadores.domain.usecase.cart.badge.UserCartBadgeUseCase
import com.virtualworld.scorganizadores.ui.uiData.UserCartUiData
import com.virtualworld.scorganizadores.common.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val deleteCartUseCase: DeleteUserCartUseCase,
    private val mapper: ProductListMapper<UserCartEntity, UserCartUiData>,
    private val singleMapper: ProductBaseMapper<UserCartUiData, UserCartEntity>,
    private val badgeUseCase: UserCartBadgeUseCase,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {


    private val _userCarts = MutableLiveData<ScreenStateProducts<List<UserCartUiData>>>()
    val userCarts: LiveData<ScreenStateProducts<List<UserCartUiData>>> get() = _userCarts

    private val _totalPriceLiveData: MutableLiveData<Double> = MutableLiveData(0.0)
    val totalPriceLiveData: LiveData<Double> get() = _totalPriceLiveData

    private val _badgeCountState = MutableStateFlow<Int>(value = 0)
    val badgeCount: StateFlow<Int> get() = _badgeCountState.asStateFlow()

    init {
        getCartsByUserId()
        getBadgeCount()
    }

    private fun getCartsByUserId() {
        viewModelScope.launch {
            cartUseCase(getUserIdFromSharedPref(sharedPreferences)).collect {
                when (it) {
                    is NetworkResponseState.Error -> _userCarts.postValue(ScreenStateProducts.Error(it.exception.message!!))
                    is NetworkResponseState.Loading -> _userCarts.postValue(ScreenStateProducts.Loading)
                    is NetworkResponseState.Success -> {
                        _userCarts.postValue(ScreenStateProducts.Success(mapper.map(it.result)))
                        updateBadgeCount(it.result.size)
                    }
                }
            }
        }
    }

    fun updateTotalPrice(cartList: List<UserCartUiData>) {
        viewModelScope.launch {
            _totalPriceLiveData.postValue(calculateTotalPrice(cartList))
        }
    }

    fun updateBadgeCount(newCount: Int) {
        viewModelScope.launch {
            _badgeCountState.value = newCount
        }
    }
    fun deleteUserCartItem(userCartUiData: UserCartUiData) {
        viewModelScope.launch {
            deleteCartUseCase(singleMapper.map(userCartUiData))
            getCartsByUserId()
        }
    }
    fun updateUserCartItem(userCartUiData: UserCartUiData) {
        viewModelScope.launch {
            updateCartUseCase(singleMapper.map(userCartUiData))
            getCartsByUserId()
        }
    }
    private fun getBadgeCount() {
        viewModelScope.launch {
            badgeUseCase(getUserIdFromSharedPref(sharedPreferences)).collectLatest {
                when (it) {
                    is NetworkResponseState.Error -> {}
                    is NetworkResponseState.Loading -> {}
                    is NetworkResponseState.Success -> {
                        _badgeCountState.value = it.result
                    }
                }
            }
        }
    }

    private fun calculateTotalPrice(cartList: List<UserCartUiData>): Double {
        var totalPrice = 0.0
        for (cart in cartList) {
            totalPrice += cart.price * cart.quantity
        }
        return totalPrice
    }





}
