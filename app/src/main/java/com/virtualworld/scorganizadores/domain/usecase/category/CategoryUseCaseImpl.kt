package com.virtualworld.scorganizadores.domain.usecase.category

import com.virtualworld.scorganizadores.common.NetworkResponseState
import com.virtualworld.scorganizadores.data.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    CategoryUseCase {
    override fun invoke(): Flow<NetworkResponseState<List<String>>> {
        return repository.getAllCategoriesListFromApi()
    }
}
