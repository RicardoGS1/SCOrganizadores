package com.virtualworld.scorganizadores.domain.usecase.user

import com.virtualworld.scorganizadores.data.repository.FirebaseRepository
import javax.inject.Inject

class SignOutUseCaseImp @Inject constructor(  private val repository: FirebaseRepository,):SignOutUseCase
{
    override fun invoke()
    {
        repository.SignOut()
    }
}