package com.compose_app_sample_1.presentation.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.compose_app_sample_1.presentation.model.PeopleResponseUI
import com.compose_app_sample_1.presentation.viewmodel.PeopleViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class PeopleListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun peopleList_displaysItems() {
        val viewModel = mockk<PeopleViewModel>(relaxed = true)
        val people = listOf(
            PeopleResponseUI.PeopleUI(1, "19BBY", "Luke Skywalker", "male", "film1")
        )
        every { viewModel.peoplePagingFlow } returns flowOf(PagingData.Companion.from(people))

        composeTestRule.setContent {
            val navController = rememberNavController()
            PeopleList(navController = navController, viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Luke Skywalker").assertIsDisplayed()
        composeTestRule.onNodeWithText("19BBY").assertIsDisplayed()
    }
}