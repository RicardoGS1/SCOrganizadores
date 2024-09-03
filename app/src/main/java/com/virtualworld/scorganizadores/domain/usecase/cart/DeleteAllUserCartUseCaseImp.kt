package com.virtualworld.scorganizadores.domain.usecase.cart

import com.virtualworld.scorganizadores.data.repository.LocalRepository
import javax.inject.Inject

class DeleteAllUserCartUseCaseImp @Inject constructor(private val repository: LocalRepository) :DeleteAllUserCartUseCase
{
    override suspend fun invoke()
    {
        repository.deleteAllUserCart()
    }


}