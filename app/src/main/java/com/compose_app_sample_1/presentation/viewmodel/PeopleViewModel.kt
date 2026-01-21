package com.compose_app_sample_1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose_app_sample_1.domain.usecases.GetPeopleUseCase
import com.compose_app_sample_1.presentation.model.PeopleResponseUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleUseCase: GetPeopleUseCase) :
    ViewModel() {

    private val _people = MutableStateFlow<PeopleResponseUI?>(null)
    var people = _people.asStateFlow()

    init {
        getPeoples(1)
    }

    private fun getPeoples(page: Int) {
        viewModelScope.launch {
            getPeopleUseCase(page).collect {

                // simulating network delay
                delay(3000)

                _people.value = PeopleResponseUI(
                    count = it.count,
                    people = it.people.map {
                        PeopleResponseUI.PeopleUI(

                            birthYear = it.birthYear,
                            name = it.name,
                            gender = it.gender,

                            films = it.films.joinToString(",")
                        )
                    })
            }
        }
    }
}