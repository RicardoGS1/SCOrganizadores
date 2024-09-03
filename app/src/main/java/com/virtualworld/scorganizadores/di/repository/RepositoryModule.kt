package com.virtualworld.scorganizadores.di.repository

import com.virtualworld.scorganizadores.data.repository.LocalRepository
import com.virtualworld.scorganizadores.data.repository.RemoteRepository
import com.virtualworld.scorganizadores.data.repository.LocalRepositoryImpl
import com.virtualworld.scorganizadores.data.repository.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule
{

    @Binds
    @ViewModelScoped
    abstract fun bindRemoteRepository(
        repository: RemoteRepositoryImpl,
    ): RemoteRepository

    @Binds
    @ViewModelScoped
    abstract fun bindLocalRepository(
        repository: LocalRepositoryImpl,
    ): LocalRepository


}
