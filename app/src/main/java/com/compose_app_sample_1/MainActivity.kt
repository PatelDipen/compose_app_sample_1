package com.compose_app_sample_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose_app_sample_1.presentation.screen.PeopleDetail
import com.compose_app_sample_1.presentation.screen.PeopleList
import com.compose_app_sample_1.ui.theme.Compose_app_sample_1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_app_sample_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppNavGraph()
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homeGraph") {

        // Home navigation
        navigation(startDestination = "peopleList", route = "homeGraph") {
            composable("peopleList") { PeopleList(navController) }
            composable(
                "peopleDetail" + "?people_id={people_id}", arguments = listOf(
                    navArgument("people_id") {
                        type = NavType.IntType
                        nullable = false
                    }
                )) { backStackEntry ->
                PeopleDetail(navController,
                    backStackEntry.arguments?.getInt("people_id")
                )
            }
        }
    }
}