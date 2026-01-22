package com.compose_app_sample_1.domain.usecases

import androidx.paging.PagingData
import com.compose_app_sample_1.domain.model.PeopleResponseDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(private val repository: PeopleRepository) {
    operator fun invoke(): Flow<PagingData<PeopleResponseDomain.PeopleDomain>> =
        repository.getPeoplePager().flow
}