package com.compose_app_sample_1.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose_app_sample_1.presentation.component.ErrorItem
import com.compose_app_sample_1.presentation.component.LoadingItem
import com.compose_app_sample_1.presentation.model.PeopleResponseUI
import com.compose_app_sample_1.presentation.viewmodel.PeopleViewModel

@Composable
fun PeopleList(
    navController: NavHostController,
    viewModel: PeopleViewModel = hiltViewModel()
) {

    val people = viewModel.peoplePagingFlow.collectAsLazyPagingItems()

    LazyColumn {
        items(people.itemCount, key = { index -> people[index]?.id ?: index }) { person ->
            people[person]?.let {
                PersonRow(it) {
                    navController.navigate("peopleDetail?people_id=${it.id}")
                }
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
fun PersonRow(person: PeopleResponseUI.PeopleUI, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("people_list_card")
            .clickable(enabled = true, onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = person.name, modifier = Modifier.testTag("people_name"))
            Text(text = person.birthYear, modifier = Modifier.testTag("people_birth_year"))
            Text(text = person.gender, modifier = Modifier.testTag("people_gender"))
        }
    }
}