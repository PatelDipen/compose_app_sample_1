package com.compose_app_sample_1.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.compose_app_sample_1.presentation.component.ErrorItem
import com.compose_app_sample_1.presentation.component.LoadingItem
import com.compose_app_sample_1.presentation.model.PeopleDetailState
import com.compose_app_sample_1.presentation.model.PeopleDetailUI
import com.compose_app_sample_1.presentation.viewmodel.PeopleDetailViewModel

@Composable
fun PeopleDetail(
    navController: NavHostController,
    id: Int?,
    viewModel: PeopleDetailViewModel = hiltViewModel()
) {
    val peopleDetail by viewModel.peopleDetail.collectAsStateWithLifecycle()

    when (peopleDetail) {
        is PeopleDetailState.Loading -> {
            LoadingItem()
        }

        is PeopleDetailState.Success -> {
            PeopleDetailCard(peopleDetails = (peopleDetail as PeopleDetailState.Success).peopleDetail) {}
        }

        is PeopleDetailState.Error -> {
            ErrorItem(message = (peopleDetail as PeopleDetailState.Error).message)
        }
    }
}

@Composable
fun PeopleDetailCard(peopleDetails: PeopleDetailUI, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("people_detail_card")
            .clickable(enabled = true, onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = peopleDetails.name, modifier = Modifier.testTag("people_name"))
            Text(text = peopleDetails.birthYear, modifier = Modifier.testTag("people_birth_year"))
            Text(text = peopleDetails.gender, modifier = Modifier.testTag("people_gender"))
        }
    }
}
