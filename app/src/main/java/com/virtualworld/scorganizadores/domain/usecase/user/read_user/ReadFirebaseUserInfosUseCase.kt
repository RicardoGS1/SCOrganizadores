package com.virtualworld.scorganizadores.domain.usecase.user.read_user

import com.virtualworld.scorganizadores.domain.entity.user.UserInformationEntity

interface ReadFirebaseUserInfosUseCase {
    operator fun invoke(
        userId: String,
        onSuccess: (UserInformationEntity) -> Unit,
        onFailure: (String) -> Unit,
    )
}
