package com.compose_app_sample_1.fake

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose_app_sample_1.domain.model.PeopleResponseDomain

class FakePeoplePagingSource(
    private val peopleList: List<PeopleResponseDomain.PeopleDomain>,
    private val shouldReturnError: Boolean
) : PagingSource<Int, PeopleResponseDomain.PeopleDomain>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PeopleResponseDomain.PeopleDomain> {

        if (shouldReturnError) {
            return LoadResult.Error(Exception("Paging error"))
        }

        return LoadResult.Page(
            data = peopleList,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(
        state: PagingState<Int, PeopleResponseDomain.PeopleDomain>
    ): Int? = null
}
