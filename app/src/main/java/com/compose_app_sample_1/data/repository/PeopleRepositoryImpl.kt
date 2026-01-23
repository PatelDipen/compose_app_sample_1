package com.compose_app_sample_1.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.compose_app_sample_1.data.mapper.PeopleDetailMapper
import com.compose_app_sample_1.data.paging.PeoplePagingSource
import com.compose_app_sample_1.data.remote.PeopleAPI
import com.compose_app_sample_1.domain.model.PeopleDetailDomain
import com.compose_app_sample_1.domain.model.PeopleResponseDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import com.compose_app_sample_1.utils.DomainResult
import com.compose_app_sample_1.utils.Failure
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val api: PeopleAPI) : PeopleRepository {

    override fun getPeoplePager(): Pager<Int, PeopleResponseDomain.PeopleDomain> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PeoplePagingSource(
                    api = api
                )
            }
        )
    }

    override suspend fun getPeopleDetail(id: Int): DomainResult<PeopleDetailDomain> {
        return try {
            val response = api.getPeople(id)

            // convert large API response data to small database object
            // we are not storing any database yet however if we are using any local DB then this is right place to store data
            val entities = PeopleDetailMapper.dtoToDatabaseEntity(response)

            // we need to pass domain object to UseCase layer so we are converting database object to domain object
            val domain = PeopleDetailMapper.databaseToDomain(entities)

            // NOTE: In our case all object are more or less similar so we dont need all this conversion
            // this is just for our understanding that on which layer data should be converted which format

            DomainResult.Success(domain)
        } catch (e: Exception) {
            DomainResult.Error(Failure.Unknown(e.message ?: "An unexpected error occurred"))
        }
    }
}