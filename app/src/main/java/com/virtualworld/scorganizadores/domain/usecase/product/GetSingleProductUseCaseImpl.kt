package com.virtualworld.scorganizadores.domain.usecase.product

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.domain.entity.product.DetailProductEntity
import com.virtualworld.scorganizadores.data.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSingleProductUseCaseImpl @Inject constructor(
    private val repository: RemoteRepository,
) : GetSingleProductUseCase {
    override fun invoke(id: Int): Flow<NetworkResponseState<DetailProductEntity>> =
        repository.getSingleProductByIdFromApi(id)
}
