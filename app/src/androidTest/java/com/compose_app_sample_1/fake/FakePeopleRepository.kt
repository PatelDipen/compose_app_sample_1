package com.compose_app_sample_1.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.compose_app_sample_1.domain.model.PeopleDetailDomain
import com.compose_app_sample_1.domain.model.PeopleResponseDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import com.compose_app_sample_1.utils.DomainResult
import com.compose_app_sample_1.utils.Failure

class FakePeopleRepository : PeopleRepository {

    var peopleList: List<PeopleResponseDomain.PeopleDomain> = emptyList()
    var shouldReturnError: Boolean = false

    override fun getPeoplePager(): Pager<Int, PeopleResponseDomain.PeopleDomain> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                FakePeoplePagingSource(
                    peopleList = peopleList,
                    shouldReturnError = shouldReturnError
                )
            }
        )
    }

    var result: DomainResult<PeopleDetailDomain> = DomainResult.Error(Failure.Unknown("Error"))
    override suspend fun getPeopleDetail(id: Int): DomainResult<PeopleDetailDomain> {
        return result
    }
}