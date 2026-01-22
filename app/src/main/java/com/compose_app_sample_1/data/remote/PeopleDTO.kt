package com.compose_app_sample_1.data.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Response with all values from API
@Serializable
data class PeopleDTO(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<Result>
) {
    @Serializable
    data class Result(
        @SerialName("birth_year")
        val birthYear: String,
        @SerialName("created")
        val created: String,
        @SerialName("edited")
        val edited: String,
        @SerialName("eye_color")
        val eyeColor: String,
        @SerialName("films")
        val films: List<String>,
        @SerialName("gender")
        val gender: String,
        @SerialName("hair_color")
        val hairColor: String,
        @SerialName("height")
        val height: String,
        @SerialName("homeworld")
        val homeworld: String,
        @SerialName("mass")
        val mass: String,
        @SerialName("name")
        val name: String,
        @SerialName("skin_color")
        val skinColor: String,
        @SerialName("species")
        val species: List<String>,
        @SerialName("starships")
        val starships: List<String>,
        @SerialName("url")
        val url: String,
        @SerialName("vehicles")
        val vehicles: List<String>
    )
}