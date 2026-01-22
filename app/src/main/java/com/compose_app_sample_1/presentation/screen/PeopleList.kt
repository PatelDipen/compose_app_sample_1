package com.compose_app_sample_1.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose_app_sample_1.presentation.model.PeopleResponseUI
import com.compose_app_sample_1.presentation.viewmodel.PeopleViewModel

@Composable
fun PeopleList(viewModel: PeopleViewModel = hiltViewModel()) {

    val people = viewModel.peoplePagingFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(people.itemCount) { person ->
            people[person]?.let {
                PersonRow(it)
            }
        }

        people.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }

                loadState.refresh is LoadState.Error -> {
                    item { ErrorItem("Failed to load") }
                }

                loadState.append is LoadState.Error -> {
                    item { ErrorItem("Failed to load more") }
                }
            }
        }
    }
}

@Composable
fun PersonRow(person: PeopleResponseUI.PeopleUI) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = person.name)
        Text(text = person.birthYear)
        Text(text = person.gender)
//        Text(text = person.films)
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
}

@Composable
fun ErrorItem(message: String) {
    Text(
        text = message,
        modifier = Modifier.padding(16.dp),
        color = Color.Red
    )
}