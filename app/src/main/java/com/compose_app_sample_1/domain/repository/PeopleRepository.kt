package com.compose_app_sample_1.domain.repository

import androidx.paging.Pager
import com.compose_app_sample_1.domain.model.PeopleDetailDomain
import com.compose_app_sample_1.domain.model.PeopleResponseDomain.PeopleDomain

interface PeopleRepository {
    fun getPeoplePager(): Pager<Int, PeopleDomain>

    suspend fun getPeopleDetail(id: Int): PeopleDetailDomain
}

