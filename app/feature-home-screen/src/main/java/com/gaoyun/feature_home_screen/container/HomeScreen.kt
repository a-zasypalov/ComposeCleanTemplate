package com.gaoyun.feature_home_screen.container

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gaoyun.cct.common.NavigationKeys
import com.gaoyun.cct.feature_user_details.UserDetailsDestination
import com.gaoyun.feature_home_screen.R
import com.gaoyun.feature_home_screen.repositories.RepositoriesScreen
import com.gaoyun.feature_home_screen.users.UsersScreenDestination

sealed class HomeScreenTabs(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Users : HomeScreenTabs(NavigationKeys.Route.USERS_LIST, R.string.users, Icons.Filled.Person)
    object Repositories : HomeScreenTabs(NavigationKeys.Route.REPOS_LIST, R.string.repositories, Icons.Filled.List)
}

val homeTabs = listOf(
    HomeScreenTabs.Users,
    HomeScreenTabs.Repositories,
)

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                homeTabs.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = HomeScreenTabs.Users.route, Modifier.padding(innerPadding)) {
            //Tabs
            composable(HomeScreenTabs.Users.route) {
                UsersScreenDestination(navController)
            }
            composable(HomeScreenTabs.Repositories.route) {
                RepositoriesScreen(navController)
            }

            //Other destinations
            composable(
                route = NavigationKeys.Route.USER_DETAILS,
                arguments = listOf(navArgument(NavigationKeys.Arg.USER_ID) {
                    type = NavType.StringType
                })
            ) {
                UserDetailsDestination(navController)
            }
        }
    }
}