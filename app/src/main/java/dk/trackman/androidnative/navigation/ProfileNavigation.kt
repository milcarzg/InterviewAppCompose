package dk.trackman.androidnative.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import dk.trackman.androidnative.feature.friends.ui.ProfileScreenRoute
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI

const val PROFILE_ROUTE = "profile_route/{nickname}"

fun NavController.navigateToProfile(nickname: String) {
    println("NAVIGATING TO PROFILE" + nickname)
    this.navigate("profile_route/$nickname")
}

fun NavGraphBuilder.profileScreen() {
    composable(route = PROFILE_ROUTE) { backStackEntry ->
        val nickname = backStackEntry.arguments?.getString("nickname")
        if (nickname != null) {
            ProfileScreenRoute(nickname = nickname)
        }
    }
}