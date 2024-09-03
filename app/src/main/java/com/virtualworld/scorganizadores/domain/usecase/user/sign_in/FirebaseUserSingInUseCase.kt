package com.virtualworld.scorganizadores.domain.usecase.user.sign_in

import com.virtualworld.scorganizadores.domain.entity.user.FirebaseSignInUserEntity
import com.virtualworld.scorganizadores.domain.entity.user.UserInformationEntity

interface FirebaseUserSingInUseCase {
    operator fun invoke(
        user: FirebaseSignInUserEntity,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit
    )
}