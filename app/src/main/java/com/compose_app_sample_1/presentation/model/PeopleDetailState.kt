package com.compose_app_sample_1.presentation.model

sealed interface PeopleDetailState {
    object Loading : PeopleDetailState
    data class Success(val peopleDetail: PeopleDetailUI) : PeopleDetailState
    data class Error(val message: String) : PeopleDetailState
}