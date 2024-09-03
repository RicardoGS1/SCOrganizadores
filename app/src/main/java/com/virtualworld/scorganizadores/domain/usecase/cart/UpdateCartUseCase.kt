package com.virtualworld.scorganizadores.domain.usecase.cart // ktlint-disable package-name

import com.virtualworld.scorganizadores.domain.entity.cart.UserCartEntity

interface UpdateCartUseCase {
    suspend operator fun invoke(userCartEntity: UserCartEntity)
}
