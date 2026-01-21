package com.compose_app_sample_1.di

import com.compose_app_sample_1.data.remote.PeopleAPI
import com.compose_app_sample_1.data.repository.PeopleRepositoryImpl
import com.compose_app_sample_1.domain.repository.PeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePeopleRepository(
        api: PeopleAPI,
    ): PeopleRepository =
        PeopleRepositoryImpl(api)
}