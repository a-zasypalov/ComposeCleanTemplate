package com.gaoyun.cct.di

import com.gaoyun.cct.data.network.UserRoutes
import com.gaoyun.cct.data.repository.UserRepositoryImpl
import com.gaoyun.cct.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserModule {

    @ViewModelScoped
    @Provides
    fun provideUserRepository(routes: UserRoutes): UserRepository = UserRepositoryImpl(routes)

}