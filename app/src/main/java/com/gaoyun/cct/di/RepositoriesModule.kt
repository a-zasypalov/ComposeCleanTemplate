package com.gaoyun.cct.di

import com.gaoyun.cct.data.network.RepositoryRoutes
import com.gaoyun.cct.data.repository.RepositoriesRepositoryImpl
import com.gaoyun.cct.domain.repository.RepositoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoriesModule {

    @ViewModelScoped
    @Provides
    fun provideRepositoriesRepository(routes: RepositoryRoutes): RepositoriesRepository = RepositoriesRepositoryImpl(routes)

}