package com.compose_app_sample_1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.compose_app_sample_1.domain.usecases.GetPeopleUseCase
import com.compose_app_sample_1.presentation.model.PeopleResponseUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleUseCase: GetPeopleUseCase) :
    ViewModel() {

//    private val _people = MutableStateFlow<PeopleResponseUI?>(null)
//    var people = _people.asStateFlow()

//    init {
//        getPeoples(1)
//    }

//    private fun getPeoples(page: Int) {
//        viewModelScope.launch {
//            getPeopleUseCase(page).collect {
//
//                // simulating network delay
//                delay(3000)
//
//                _people.value = PeopleResponseUI(
//                    count = it.count,
//                    people = it.people.map {
//                        PeopleResponseUI.PeopleUI(
//
//                            birthYear = it.birthYear,
//                            name = it.name,
//                            gender = it.gender,
//
//                            films = it.films.joinToString(",")
//                        )
//                    })
//            }
//        }
//    }

    val peoplePagingFlow: Flow<PagingData<PeopleResponseUI.PeopleUI>> =
        getPeopleUseCase().map { pagingData ->
            pagingData.map {
                PeopleResponseUI.PeopleUI(
                    birthYear = it.birthYear,
                    name = it.name,
                    gender = it.gender,
                    films = it.films.joinToString(",")
                )
            }
        }.cachedIn(viewModelScope)
}