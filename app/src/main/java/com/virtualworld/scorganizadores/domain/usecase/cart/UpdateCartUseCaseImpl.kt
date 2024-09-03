package com.virtualworld.scorganizadores.domain.usecase.cart // ktlint-disable package-name

import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity
import com.virtualworld.scorganizadores.data.repository.LocalRepository
import javax.inject.Inject

class UpdateCartUseCaseImpl @Inject constructor(
    private val repository: LocalRepository,
) : UpdateCartUseCase {
    override suspend fun invoke(userCartEntity: UserCartEntity) {
        repository.updateUserCart(userCartEntity)
    }
}
