package com.compose_app_sample_1.domain.repository

import androidx.paging.Pager
import com.compose_app_sample_1.domain.model.PeopleDetailDomain
import com.compose_app_sample_1.domain.model.PeopleResponseDomain.PeopleDomain
import com.compose_app_sample_1.utils.Resource

interface PeopleRepository {
    fun getPeoplePager(): Pager<Int, PeopleDomain>

    suspend fun getPeopleDetail(id: Int): Resource<PeopleDetailDomain>
}

