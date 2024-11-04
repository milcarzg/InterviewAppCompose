package dk.trackman.androidnative.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dk.trackman.androidnative.feature.friends.FriendsScreenRoute

const val FRIENDS_ROUTE = "friends_route"

fun NavController.navigateToFriends(navOptions: NavOptions? = null) {
    this.navigate(FRIENDS_ROUTE, navOptions)
}

fun NavGraphBuilder.friendsScreen(
) {
    composable(route = FRIENDS_ROUTE) {
        FriendsScreenRoute()
    }
}
