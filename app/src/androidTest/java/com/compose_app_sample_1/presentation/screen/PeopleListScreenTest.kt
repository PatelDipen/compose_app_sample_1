package com.compose_app_sample_1.presentation.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.compose_app_sample_1.domain.model.PeopleResponseDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import com.compose_app_sample_1.fake.FakePeopleRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class PeopleListScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Inject
    lateinit var repository: PeopleRepository

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun peopleList_showsLoading() {
        (repository as FakePeopleRepository).peopleList = emptyList()

        composeRule.setContent {
            PeopleList(
                navController = rememberNavController()
            )
        }

        composeRule
            .onNodeWithTag("loading")
            .assertIsDisplayed()
    }

    @Test
    fun peopleList_displaysPeople() {
        (repository as FakePeopleRepository).peopleList =
            listOf(
                PeopleResponseDomain.PeopleDomain(
                    id = 1,
                    name = "Luke Skywalker",
                    birthYear = "19BBY",
                    gender = "male",
                    films = emptyList()
                ),
                PeopleResponseDomain.PeopleDomain(
                    id = 2,
                    name = "Leia Organa",
                    birthYear = "19BBY",
                    gender = "female",
                    films = emptyList()
                )
            )

        composeRule.setContent {
            PeopleList(
                navController = rememberNavController()
            )
        }

        composeRule
            .onAllNodesWithTag("people_list_card")
            .assertCountEquals(2)

        composeRule
            .onNodeWithText("Luke Skywalker")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Leia Organa")
            .assertIsDisplayed()
    }

    @Test
    fun peopleList_showsErrorOnFailure() {
        (repository as FakePeopleRepository).shouldReturnError = true

        composeRule.setContent {
            PeopleList(
                navController = rememberNavController()
            )
        }

        composeRule
            .onNodeWithText("Failed to load")
            .assertIsDisplayed()
    }

}
