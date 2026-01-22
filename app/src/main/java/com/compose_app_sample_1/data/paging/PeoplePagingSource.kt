package com.compose_app_sample_1.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose_app_sample_1.data.mapper.PeopleMapper
import com.compose_app_sample_1.data.remote.PeopleAPI
import com.compose_app_sample_1.domain.model.PeopleResponseDomain

class PeoplePagingSource(
    private val api: PeopleAPI
) : PagingSource<Int, PeopleResponseDomain.PeopleDomain>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PeopleResponseDomain.PeopleDomain> {
        return try {
            val page = params.key ?: 1
            val response = api.getPeoples(page)

            // convert large API response data to small database object
            // we are not storing any database yet however if we are using any local DB then this is right place to store data
            val entities = PeopleMapper.dtoToDatabaseEntity(response)

            // we need to pass domain object to UseCase layer so we are converting database object to domain object
            val domain = PeopleMapper.databaseToDomain(entities)

            // NOTE: In our case all object are more or less similar so we dont need all this conversion
            // this is just for our understanding that on which layer data should be converted which format

            LoadResult.Page(
                data = domain.people,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, PeopleResponseDomain.PeopleDomain>
    ): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}