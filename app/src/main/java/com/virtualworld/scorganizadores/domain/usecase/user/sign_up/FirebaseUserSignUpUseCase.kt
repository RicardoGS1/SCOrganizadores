package com.virtualworld.scorganizadores.domain.usecase.user.sign_up

import com.virtualworld.scorganizadores.domain.entity.user.UserInformationEntity

interface FirebaseUserSignUpUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )
}
