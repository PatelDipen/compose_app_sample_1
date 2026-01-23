package com.compose_app_sample_1.presentation.screen.mockktest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.compose_app_sample_1.presentation.model.PeopleDetailState
import com.compose_app_sample_1.presentation.model.PeopleDetailUI
import com.compose_app_sample_1.presentation.screen.PeopleDetail
import com.compose_app_sample_1.presentation.viewmodel.PeopleDetailViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class PeopleDetailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun peopleDetail_loadingState_displaysLoading() {
        val viewModel = mockk<PeopleDetailViewModel>(relaxed = true)
        every { viewModel.peopleDetail } returns MutableStateFlow(PeopleDetailState.Loading)

        composeTestRule.setContent {
            val navController = rememberNavController()
            PeopleDetail(navController = navController, id = 1, viewModel = viewModel)
        }

        composeTestRule
            .onNodeWithTag("loading")
            .assertIsDisplayed()
    }

    @Test
    fun peopleDetail_successState_displaysDetails() {
        val viewModel = mockk<PeopleDetailViewModel>(relaxed = true)
        val details = PeopleDetailUI(
            birthYear = "19BBY",
            films = listOf("A New Hope"),
            gender = "male",
            hairColor = "blond",
            height = "172",
            homeworld = "Tatooine",
            mass = "77",
            name = "Luke Skywalker",
            skinColor = "fair"
        )
        every { viewModel.peopleDetail } returns MutableStateFlow(PeopleDetailState.Success(details))

        composeTestRule.setContent {
            val navController = rememberNavController()
            PeopleDetail(navController = navController, id = 1, viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Luke Skywalker").assertIsDisplayed()
        composeTestRule.onNodeWithText("19BBY").assertIsDisplayed()
        composeTestRule.onNodeWithText("male").assertIsDisplayed()
    }

    @Test
    fun peopleDetail_errorState_displaysError() {
        val viewModel = mockk<PeopleDetailViewModel>(relaxed = true)
        val errorMessage = "Failed to fetch details"
        every { viewModel.peopleDetail } returns MutableStateFlow(
            PeopleDetailState.Error(
                errorMessage
            )
        )

        composeTestRule.setContent {
            val navController = rememberNavController()
            PeopleDetail(navController = navController, id = 1, viewModel = viewModel)
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }
}