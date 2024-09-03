package com.virtualworld.scorganizadores.domain.usecase.user.sign_up

import com.virtualworld.scorganizadores.domain.entity.user.UserInformationEntity
import com.virtualworld.scorganizadores.data.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseUserSignUpUseCaseImpl @Inject constructor(private val firebaseRepository: FirebaseRepository,) : FirebaseUserSignUpUseCase {



    override fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        firebaseRepository.signUpWithFirebase(user, onSuccess, onFailure)
    }
}
