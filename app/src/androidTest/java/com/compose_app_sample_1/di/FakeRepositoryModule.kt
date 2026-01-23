package com.compose_app_sample_1.di

import com.compose_app_sample_1.domain.repository.PeopleRepository
import com.compose_app_sample_1.fake.FakePeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    fun providePeopleRepository(): PeopleRepository =
        FakePeopleRepository()
}