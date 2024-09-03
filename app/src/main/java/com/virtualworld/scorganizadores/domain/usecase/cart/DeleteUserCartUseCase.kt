package com.virtualworld.scorganizadores.domain.usecase.cart

import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity

interface DeleteUserCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
