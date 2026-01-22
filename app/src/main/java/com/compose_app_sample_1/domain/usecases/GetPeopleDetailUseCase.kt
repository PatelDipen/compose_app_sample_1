package com.compose_app_sample_1.domain.usecases

import com.compose_app_sample_1.domain.model.PeopleDetailDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import com.compose_app_sample_1.utils.Resource
import javax.inject.Inject

class GetPeopleDetailUseCase @Inject constructor(private val repository: PeopleRepository) {
    suspend operator fun invoke(id: Int): Resource<PeopleDetailDomain> =
        repository.getPeopleDetail(id)
}