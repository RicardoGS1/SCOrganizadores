package com.virtualworld.scorganizadores.domain.usecase.cart.badge

import com.virtualworld.scorganizadores.common.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface UserCartBadgeUseCase {
    suspend operator fun invoke(userId: String): Flow<NetworkResponseState<Int>>
}