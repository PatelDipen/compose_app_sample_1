package com.compose_app_sample_1.presentation.screen.mockktest

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import com.compose_app_sample_1.presentation.model.PeopleResponseUI
import com.compose_app_sample_1.presentation.screen.PeopleList
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
            PeopleResponseUI.PeopleUI(1, "19BBY", "Luke Skywalker", "male", "film1"),
            PeopleResponseUI.PeopleUI(id = 2, name = "Leia Organa", birthYear = "19BBZ", gender = "female", films = "film1")
        )
        every { viewModel.peoplePagingFlow } returns flowOf(PagingData.from(people))

        composeTestRule.setContent {
            val navController = rememberNavController()
            PeopleList(navController = navController, viewModel = viewModel)
        }

        composeTestRule
            .onAllNodesWithTag("people_list_card")
            .assertCountEquals(2)

        composeTestRule.onNodeWithText("Luke Skywalker").assertIsDisplayed()
        composeTestRule.onNodeWithText("19BBY").assertIsDisplayed()

        composeTestRule.onNodeWithText("Leia Organa").assertIsDisplayed()
        composeTestRule.onNodeWithText("19BBZ").assertIsDisplayed()
    }
}