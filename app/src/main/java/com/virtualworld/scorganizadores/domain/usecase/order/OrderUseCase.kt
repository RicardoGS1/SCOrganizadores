package com.virtualworld.scorganizadores.domain.usecase.order

import com.virtualworld.scorganizadores.domain.entity.order.OrderInfoEntity

interface OrderUseCase
{
    suspend operator fun invoke(orderInfoEntity: OrderInfoEntity, onSuccess: () -> Unit, onFailure: (String) -> Unit,onLoading:() -> Unit)

}