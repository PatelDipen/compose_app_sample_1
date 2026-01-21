package com.compose_app_sample_1.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.compose_app_sample_1.presentation.viewmodel.PeopleViewModel

@Composable
fun PeopleList(viewModel: PeopleViewModel = hiltViewModel()) {

    val peopleList by viewModel.people.collectAsStateWithLifecycle()

    peopleList?.let {
        LazyColumn {
            items(it.people, key = { person -> person.name }) { person ->
                Text(person.name)
            }
        }
    } ?: run {
        Text("Loading...")
    }
}