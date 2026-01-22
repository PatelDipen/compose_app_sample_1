package com.compose_app_sample_1.data.mapper

import com.compose_app_sample_1.data.local.PeopleDetailDBEntity
import com.compose_app_sample_1.data.remote.PeopleDetailDTO
import com.compose_app_sample_1.domain.model.PeopleDetailDomain

object PeopleDetailMapper {

    // convert large API response data to small object
    fun dtoToDatabaseEntity(dto: PeopleDetailDTO): PeopleDetailDBEntity = PeopleDetailDBEntity(
        dto.birthYear,
        dto.films,
        dto.gender,
        dto.hairColor,
        dto.height,
        dto.homeworld,
        dto.mass,
        dto.name,
        dto.skinColor
    )

    // convert database entity to domain object
    fun databaseToDomain(dto: PeopleDetailDBEntity): PeopleDetailDomain = PeopleDetailDomain(
        dto.birthYear,
        dto.films,
        dto.gender,
        dto.hairColor,
        dto.height,
        dto.homeworld,
        dto.mass,
        dto.name,
        dto.skinColor
    )
}