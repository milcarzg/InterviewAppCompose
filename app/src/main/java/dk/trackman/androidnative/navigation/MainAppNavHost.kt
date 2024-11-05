package dk.trackman.androidnative.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import dk.trackman.androidnative.main.MainAppState

@Composable
fun MainAppNavHost(
    appState: MainAppState,
    startDestination: String = FRIENDS_ROUTE,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        friendsScreen(navController)
        profileScreen(navController)
    }
}
