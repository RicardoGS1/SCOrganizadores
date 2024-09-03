package com.virtualworld.scorganizadores.domain.usecase.cart

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import kotlinx.coroutines.flow.Flow

interface CartUseCase {
    suspend operator fun invoke(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>> // getCartsByUserIdFromLocal

    suspend operator fun invoke(userCartEntity: UserCartEntity) // insertCartToDb
}
