package com.virtualworld.scorganizadores.domain.usecase.product

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.domain.entity.product.ProductEntity
import com.virtualworld.scorganizadores.data.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    GetAllProductsUseCase {


    override fun invoke(): Flow<NetworkResponseState<List<ProductEntity>>> {



        return repository.getProductsListFromApi()
    }

    override fun invoke(categoryName: String): Flow<NetworkResponseState<List<ProductEntity>>> {
        return repository.getProductsListByCategoryNameFromApi(categoryName)
    }
}
