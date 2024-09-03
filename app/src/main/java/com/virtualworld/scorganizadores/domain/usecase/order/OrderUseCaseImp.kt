package com.virtualworld.scorganizadores.domain.usecase.order

import android.content.SharedPreferences
import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.common.getUserIdFromSharedPref
import com.virtualworld.scorganizadores.data.repository.LocalRepository
import com.virtualworld.scorganizadores.data.repository.RemoteRepository
import com.virtualworld.scorganizadores.domain.entity.order.OrderInfoEntity
import javax.inject.Inject

class OrderUseCaseImp @Inject constructor(
    private val repositoryLocal: LocalRepository,
    private val repositoryRemote: RemoteRepository,
    private val sharedPreferences: SharedPreferences,
) : OrderUseCase
{
    override suspend fun invoke(orderInfoEntity: OrderInfoEntity, onSuccess: () -> Unit, onFailure: (String) -> Unit,onLoading:() -> Unit)
    {
        repositoryLocal.getCartsByUserIdFromDb(getUserIdFromSharedPref(sharedPreferences)).collect { cartEntry ->

            when (cartEntry)
            {
                is NetworkResponseState.Error -> onFailure(cartEntry.exception.message.toString())
                is NetworkResponseState.Loading -> onLoading()
                is NetworkResponseState.Success ->
                {

                    println("se efectuo la petticion remota")
                    repositoryRemote.setOrderForApi(orderInfoEntity, listCart = cartEntry.result, onSuccess, onFailure,onLoading)

                }
            }
        }


    }


}