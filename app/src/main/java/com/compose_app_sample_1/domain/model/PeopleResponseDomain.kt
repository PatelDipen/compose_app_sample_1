package com.compose_app_sample_1.domain.model

data class PeopleResponseDomain(val count: Int, val people: List<PeopleDomain>) {
    data class PeopleDomain(
        val id: Int,
        val birthYear: String,
        val name: String,
        val gender: String,
        val films: List<String>
    )
}
