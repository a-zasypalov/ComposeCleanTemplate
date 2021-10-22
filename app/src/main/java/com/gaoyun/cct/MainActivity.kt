package com.gaoyun.cct

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gaoyun.cct.common.NavigationKeys
import com.gaoyun.cct.common.ui.theme.ComposeCleanTemplateTheme
import com.gaoyun.cct.feature_user_details.UserDetailsDestination
import com.gaoyun.feature_home_screen.container.HomeScreen
import com.gaoyun.feature_repository_details.RepositoryDetailsDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeCleanTemplateTheme {
                Surface(color = MaterialTheme.colors.background) {
                    GlobalDestinationState()
                }
            }
        }
    }
}

@Composable
fun GlobalDestinationState() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationKeys.RouteGlobal.HOME) {
        composable(NavigationKeys.RouteGlobal.HOME) { HomeScreen(navController) }
        composable(
            route = NavigationKeys.RouteGlobal.REPOS_DETAILS_GLOBAL,
            arguments = listOf(
                navArgument(NavigationKeys.Arg.USER_ID) { type = NavType.StringType },
                navArgument(NavigationKeys.Arg.REPO_ID) { type = NavType.StringType },
            )
        ) {
            RepositoryDetailsDestination(navController)
        }
    }
}