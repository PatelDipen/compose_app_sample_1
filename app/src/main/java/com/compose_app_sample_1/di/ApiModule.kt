package com.compose_app_sample_1.di

import com.compose_app_sample_1.data.remote.PeopleAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePeopleAPI(
        retrofit: Retrofit
    ): PeopleAPI =
        retrofit.create(PeopleAPI::class.java)

}