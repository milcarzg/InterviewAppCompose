package dk.trackman.androidnative.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dk.trackman.androidnative.feature.friends.ui.screen.ProfileScreenRoute

const val PROFILE_ROUTE = "profile_route/{nickname}"

fun NavController.navigateToProfile(nickname: String) {
    println("NAVIGATING TO PROFILE" + nickname)
    this.navigate("profile_route/$nickname")
}

fun NavGraphBuilder.profileScreen() {
    composable(route = PROFILE_ROUTE) { backStackEntry ->
        val nickname = backStackEntry.arguments?.getString("nickname")
        if (nickname != null) {
            ProfileScreenRoute(nickName = nickname)
        }
    }
}