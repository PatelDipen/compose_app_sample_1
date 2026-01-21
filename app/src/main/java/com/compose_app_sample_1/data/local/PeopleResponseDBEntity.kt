package com.compose_app_sample_1.data.local

// API response is returning several data which is not required for the app.
// Creating small entity which will contain only required data from API response.
data class PeopleResponseDBEntity(val count: Int, val people: List<PeopleDBEntity>) {
    data class PeopleDBEntity(
        val birthYear: String,
        val name: String,
        val gender: String,
        val films: List<String>
    )
}
