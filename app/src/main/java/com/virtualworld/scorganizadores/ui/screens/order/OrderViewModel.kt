package com.virtualworld.scorganizadores.ui.screens.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.scorganizadores.common.ScreenStateUser
import com.virtualworld.scorganizadores.domain.entity.order.OrderInfoEntity
import com.virtualworld.scorganizadores.domain.usecase.cart.DeleteAllUserCartUseCase
import com.virtualworld.scorganizadores.domain.usecase.order.OrderUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderUseCase: OrderUseCase,private val deleteAllUserCartUseCase: DeleteAllUserCartUseCase,
) : ViewModel()
{

    private val _setOrder = MutableLiveData<ScreenStateUser>()
    val setOrderState: LiveData<ScreenStateUser> get() = _setOrder

    fun setOrder(infoOrder: OrderInfoEntity)
    {
        _setOrder.value = ScreenStateUser.Loading

        viewModelScope.launch {

            orderUseCase(
                infoOrder,
                onSuccess = {
                    deleteAllUserCart()
                    _setOrder.postValue(ScreenStateUser.Success)
                },
                onFailure = {
                    _setOrder.postValue(ScreenStateUser.Error(it))
                },
                onLoading =  {
                    _setOrder.postValue(ScreenStateUser.Loading)
                }
            )

        }

    }

    fun deleteAllUserCart(){
        viewModelScope.launch {
            deleteAllUserCartUseCase.invoke()
        }
    }

}