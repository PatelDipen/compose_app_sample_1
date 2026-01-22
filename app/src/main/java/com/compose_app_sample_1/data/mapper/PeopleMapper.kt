package com.compose_app_sample_1.data.mapper

import com.compose_app_sample_1.data.local.PeopleResponseDBEntity
import com.compose_app_sample_1.data.remote.PeopleDTO
import com.compose_app_sample_1.domain.model.PeopleResponseDomain

object PeopleMapper {

    // convert large API response data to small object
    fun dtoToDatabaseEntity(dto: PeopleDTO): PeopleResponseDBEntity = PeopleResponseDBEntity(
        dto.count,
        people = dto.results.map {

            PeopleResponseDBEntity.PeopleDBEntity(
                id = it.url.trim('/').split('/').last().toInt(),
                birthYear = it.birthYear,
                name = it.name,
                gender = it.gender,
                films = it.films
            )
        })

    // convert database entity to domain object
    fun databaseToDomain(dto: PeopleResponseDBEntity): PeopleResponseDomain = PeopleResponseDomain(
        dto.count,
        people = dto.people.map {
            PeopleResponseDomain.PeopleDomain(
                id = it.id,
                birthYear = it.birthYear,
                name = it.name,
                gender = it.gender,
                films = it.films
            )
        })
}