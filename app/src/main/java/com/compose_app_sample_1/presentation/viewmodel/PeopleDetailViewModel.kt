package com.compose_app_sample_1.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose_app_sample_1.domain.usecases.GetPeopleDetailUseCase
import com.compose_app_sample_1.presentation.model.PeopleDetailState
import com.compose_app_sample_1.presentation.model.PeopleDetailUI
import com.compose_app_sample_1.utils.DomainResult
import com.compose_app_sample_1.utils.Failure
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

    private val _peopleDetail = MutableStateFlow<PeopleDetailState>(PeopleDetailState.Loading)
    val peopleDetail: StateFlow<PeopleDetailState> = _peopleDetail

    init {
        savedStateHandle.get<Int>("people_id")?.let { id ->
            getPeopleDetail(id)
        }
    }

    private fun getPeopleDetail(id: Int) {
        viewModelScope.launch {
            _peopleDetail.value = PeopleDetailState.Loading

            when (val peopleDetail = getPeopleDetailUseCase(id)) {
                is DomainResult.Success -> {
                    peopleDetail.data?.apply {
                        _peopleDetail.value = PeopleDetailState.Success(
                            PeopleDetailUI(
                                birthYear = birthYear,
                                films = films,
                                gender = gender,
                                hairColor = hairColor,
                                height = height,
                                homeworld = homeworld,
                                mass = mass,
                                name = name,
                                skinColor = skinColor
                            )
                        )
                    }
                }

                is DomainResult.Error -> {
                    when (peopleDetail.failure) {
                        is Failure.Network -> {
                            _peopleDetail.value = PeopleDetailState.Error("Network Error")
                        }

                        is Failure.NotFound -> {
                            _peopleDetail.value = PeopleDetailState.Error("Not Found")
                        }

                        is Failure.Unknown -> {
                            _peopleDetail.value =
                                PeopleDetailState.Error(peopleDetail.failure.message)
                        }
                    }
                }
            }
        }
    }
}
