package com.virtualworld.scorganizadores.domain.usecase.cart.badge

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserCartBadgeUseCaseImpl @Inject constructor(
    private val repository: LocalRepository,
) : UserCartBadgeUseCase {
    override suspend fun invoke(userId: String): Flow<NetworkResponseState<Int>> {
        return repository.getBadgeCountFromDb(userId)
    }
}
