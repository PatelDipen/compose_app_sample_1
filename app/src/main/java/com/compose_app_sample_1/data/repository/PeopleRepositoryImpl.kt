package com.compose_app_sample_1.data.repository

import com.compose_app_sample_1.data.mapper.PeopleMapper
import com.compose_app_sample_1.data.remote.PeopleAPI
import com.compose_app_sample_1.domain.model.PeopleResponseDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val api: PeopleAPI) : PeopleRepository {

    override suspend fun getPeoples(page: Int): Flow<PeopleResponseDomain> {
        val response = api.getPeoples(page)

        // convert large API response data to small database object
        // we are not storing any database yet however if we are using any local DB then this is right place to store data
        val entities = PeopleMapper.dtoToDatabaseEntity(response)

        // we need to pass domain object to UseCase layer so we are converting database object to domain object
        val domain = PeopleMapper.databaseToDomain(entities)

        // NOTE: In our case all object are more or less similar so we dont need all this conversion
        // this is just for our understanding that on which layer data should be converted which format

        return flow {
            emit(domain)
        }
    }
}