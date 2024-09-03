package com.virtualworld.scorganizadores.di.repository

import com.virtualworld.scorganizadores.data.repository.FirebaseRepository
import com.virtualworld.scorganizadores.data.repository.FirebaseRepositoryImpl
import com.virtualworld.scorganizadores.data.source.FirebaseDataSource
import com.virtualworld.scorganizadores.data.source.FirebaseDataSourceImpl
import com.virtualworld.scorganizadores.domain.usecase.user.SignOutUseCase
import com.virtualworld.scorganizadores.domain.usecase.user.SignOutUseCaseImp
import com.virtualworld.scorganizadores.domain.usecase.user.forget_pw.ForgotPwFirebaseUserUseCase
import com.virtualworld.scorganizadores.domain.usecase.user.forget_pw.ForgotPwFirebaseUserUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.user.read_user.ReadFirebaseUserInfosUseCase
import com.virtualworld.scorganizadores.domain.usecase.user.read_user.ReadFirebaseUserInfosUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.user.sign_in.FirebaseUserSingInUseCase
import com.virtualworld.scorganizadores.domain.usecase.user.sign_in.FirebaseUserSingInUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.user.sign_up.FirebaseUserSignUpUseCase
import com.virtualworld.scorganizadores.domain.usecase.user.sign_up.FirebaseUserSignUpUseCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.user.write_user.WriteFirebaseUserInfosCaseImpl
import com.virtualworld.scorganizadores.domain.usecase.user.write_user.WriteFirebaseUserInfosUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class idModules
{

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseRepository(repository: FirebaseRepositoryImpl): FirebaseRepository

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignOutUseCase(signOutUseCaseImp: SignOutUseCaseImp): SignOutUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignUpUseCase(firebaseSignUpUseCaseImpl: FirebaseUserSignUpUseCaseImpl): FirebaseUserSignUpUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseSignInUseCase(firebaseSignInUseCaseImpl: FirebaseUserSingInUseCaseImpl): FirebaseUserSingInUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseForgetPwUseCase(firebaseForgetPwUseCaseImpl: ForgotPwFirebaseUserUseCaseImpl): ForgotPwFirebaseUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseWriteUserUseCase(firebaseWriteUserUseCaseImpl: WriteFirebaseUserInfosCaseImpl): WriteFirebaseUserInfosUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindFirebaseReadUserUseCase(firebaseReadUserCaseImpl: ReadFirebaseUserInfosUseCaseImpl): ReadFirebaseUserInfosUseCase


    @Binds
    @ViewModelScoped
    abstract fun bindDataSource(dataSource: FirebaseDataSourceImpl): FirebaseDataSource


}
