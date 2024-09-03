package com.virtualworld.scorganizadores.domain.usecase.user.write_user

import com.virtualworld.scorganizadores.domain.entity.user.UserInformationEntity

interface WriteFirebaseUserInfosUseCase {
    operator fun invoke(
        user: UserInformationEntity,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )
}
