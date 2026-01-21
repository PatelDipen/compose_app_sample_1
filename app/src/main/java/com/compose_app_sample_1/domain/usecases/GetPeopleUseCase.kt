package com.compose_app_sample_1.domain.usecases

import com.compose_app_sample_1.domain.model.PeopleResponseDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(private val repository: PeopleRepository) {
    suspend operator fun invoke(page: Int): Flow<PeopleResponseDomain> {
        return repository.getPeoples(page)
    }
}