package com.compose_app_sample_1.presentation.screen.hilttest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.compose_app_sample_1.HiltTestActivity
import com.compose_app_sample_1.domain.model.PeopleDetailDomain
import com.compose_app_sample_1.domain.repository.PeopleRepository
import com.compose_app_sample_1.fake.FakePeopleRepository
import com.compose_app_sample_1.presentation.screen.PeopleDetail
import com.compose_app_sample_1.utils.DomainResult
import com.compose_app_sample_1.utils.Failure
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class PeopleDetailScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<HiltTestActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Inject
    lateinit var repository: PeopleRepository

//    @Test
//    fun peopleDetail_loadingState_isDisplayed() {
//        (repository as FakePeopleRepository).result =
//            DomainResult.Loading()
//
//        composeRule.setContent {
//            PeopleDetail(
//                navController = rememberNavController(),
//                id = 1
//            )
//        }
//
//        composeRule
//            .onNodeWithTag("loading")
//            .assertIsDisplayed()
//    }

    @Test
    fun peopleDetail_successState_displaysData() {
        (repository as FakePeopleRepository).result =
            DomainResult.Success(
                PeopleDetailDomain(
                    name = "Luke Skywalker",
                    birthYear = "19BBY",
                    gender = "male",
                    films = emptyList(),
                    hairColor = "",
                    height = "",
                    homeworld = "",
                    mass = "",
                    skinColor = ""
                )
            )

        composeRule.setContent {
            PeopleDetail(
                navController = rememberNavController(),
                id = 1,
            )
        }

        composeRule.waitForIdle()

        composeRule.onNodeWithTag("people_detail_card")
            .assertIsDisplayed()

        composeRule.onNodeWithTag("people_name")
            .assertTextEquals("Luke Skywalker")

        composeRule.onNodeWithTag("people_birth_year")
            .assertTextEquals("19BBY")

        composeRule.onNodeWithTag("people_gender")
            .assertTextEquals("male")
    }


    @Test
    fun peopleDetail_errorState_isDisplayed() {
        (repository as FakePeopleRepository).result =
            DomainResult.Error(Failure.Unknown("Network error"))

        composeRule.setContent {
            PeopleDetail(
                navController = rememberNavController(),
                id = 1
            )
        }

        composeRule
            .onNodeWithTag("error")
            .assertIsDisplayed()
    }

}
