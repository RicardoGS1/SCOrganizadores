package com.virtualworld.scorganizadores.domain.usecase.category

import com.virtualworld.scorganizadores.common.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {
    operator fun invoke(): Flow<NetworkResponseState<List<String>>>
}
