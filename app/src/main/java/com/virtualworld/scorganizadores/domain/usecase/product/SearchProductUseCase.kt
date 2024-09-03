package com.virtualworld.scorganizadores.domain.usecase.product

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface SearchProductUseCase {
    operator fun invoke(query: String): Flow<NetworkResponseState<List<ProductEntity>>>
}
