package dk.trackman.androidnative.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dk.trackman.androidnative.feature.friends.ui.screen.FriendsScreenRoute

const val FRIENDS_ROUTE = "friends_route"

fun NavController.navigateToFriends(navOptions: NavOptions? = null) {
    this.navigate(FRIENDS_ROUTE, navOptions)
}

fun NavGraphBuilder.friendsScreen(navController: NavHostController) {
    composable(route = FRIENDS_ROUTE) {
        FriendsScreenRoute(navController)
    }
}
