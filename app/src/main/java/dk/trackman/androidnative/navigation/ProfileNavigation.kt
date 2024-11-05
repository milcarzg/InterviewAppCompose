package dk.trackman.androidnative.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dk.trackman.androidnative.feature.friends.ui.screen.ProfileScreenRoute

const val PROFILE_ROUTE = "profile_route/{nickname}"

fun NavController.navigateToProfile(nickname: String, navOptions: NavOptions? = null) {
    println("NAVIGATING TO PROFILE" + nickname)
    this.navigate("profile_route/$nickname", navOptions)
}

fun NavGraphBuilder.profileScreen(navController: NavHostController) {
    composable(route = PROFILE_ROUTE) { backStackEntry ->
        val nickname = backStackEntry.arguments?.getString("nickname")
        if (nickname != null) {
            ProfileScreenRoute(
                nickName = nickname,
                navController = navController
            )
        }
    }
}