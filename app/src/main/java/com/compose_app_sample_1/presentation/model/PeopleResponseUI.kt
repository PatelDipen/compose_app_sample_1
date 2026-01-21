package com.compose_app_sample_1.presentation.model

data class PeopleResponseUI(val count: Int, val people: List<PeopleUI>) {
    data class PeopleUI(
        val birthYear: String,
        val name: String,
        val gender: String,
        val films: String
    )
}
