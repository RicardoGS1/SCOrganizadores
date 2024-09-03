package com.virtualworld.scorganizadores.domain.usecase.product

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import com.virtualworld.scorganizadores.data.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : SearchProductUseCase {
    override fun invoke(query: String): Flow<NetworkResponseState<List<ProductEntity>>> {
        return repository.getProductsListBySearchFromApi(query)
    }
}
