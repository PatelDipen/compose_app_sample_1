package com.compose_app_sample_1.domain.repository

import com.compose_app_sample_1.domain.model.PeopleResponseDomain
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    suspend fun getPeoples(page: Int): Flow<PeopleResponseDomain>
}

