package com.compose_app_sample_1.data.local

// API response is returning several data which is not required for the app.
// Creating small entity which will contain only required data from API response.
data class PeopleDetailDBEntity(
    val birthYear: String,
    val films: List<String>,
    val gender: String,
    val hairColor: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skinColor: String,
)
