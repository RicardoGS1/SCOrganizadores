package com.virtualworld.scorganizadores.domain.usecase.cart

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import com.virtualworld.scorganizadores.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(private val repository: LocalRepository) : CartUseCase {
    override suspend fun invoke(userId: String): Flow<NetworkResponseState<List<UserCartEntity>>> = repository.getCartsByUserIdFromDb(userId)
    override suspend fun invoke(userCartEntity: UserCartEntity) {
        repository.insertUserCartToDb(userCartEntity)
    }
}
