package com.compose_app_sample_1.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose_app_sample_1.domain.usecases.GetPeopleDetailUseCase
import com.compose_app_sample_1.presentation.model.PeopleDetailUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailViewModel @Inject constructor(
    private val getPeopleDetailUseCase: GetPeopleDetailUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _peopleDetail = MutableStateFlow<PeopleDetailUI?>(null)
    val peopleDetail: StateFlow<PeopleDetailUI?> = _peopleDetail

    init {
        savedStateHandle.get<Int>("people_id")?.let { id ->
            getPeopleDetail(id)
        }
    }

    private fun getPeopleDetail(id: Int) {
        viewModelScope.launch {
            val peopleDetail = getPeopleDetailUseCase(id)
            _peopleDetail.value = PeopleDetailUI(
                birthYear = peopleDetail.birthYear,
                films = peopleDetail.films,
                gender = peopleDetail.gender,
                hairColor = peopleDetail.hairColor,
                height = peopleDetail.height,
                homeworld = peopleDetail.homeworld,
                mass = peopleDetail.mass,
                name = peopleDetail.name,
                skinColor = peopleDetail.skinColor
            )
        }
    }
}