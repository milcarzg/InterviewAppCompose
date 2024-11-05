package dk.trackman.androidnative.feature.friends.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dk.trackman.androidnative.R
import dk.trackman.androidnative.feature.friends.ui.viewmodels.FriendsViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import dk.trackman.androidnative.designsystem.component.TdsTopAppBar
import dk.trackman.androidnative.designsystem.icon.AppIcons.ChevronLeft
import dk.trackman.androidnative.designsystem.icon.AppIcons.MoreVert
import dk.trackman.androidnative.designsystem.theme.LightGrey
import dk.trackman.androidnative.designsystem.theme.TextGrey

@Composable
fun ProfileScreenRoute(nickName: String, navController: NavController)
{
    ProfileScreenContent(nickName= nickName, navController = navController)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreenContent(nickName: String, navController: NavController, viewModel: FriendsViewModel = hiltViewModel(), modifier: Modifier = Modifier) {

    val friends by viewModel.friends
    val friend = friends.find { it.nickName == nickName }
    val background = painterResource(R.drawable.profile_background)
    val graphic = painterResource(R.drawable.graphic)

    println("Screen for user: $friend")
    friend?.let {
        Box (modifier = Modifier.fillMaxSize()) {
            Image(
                painter = background,
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize()
            )
        }
        Scaffold(
            topBar = {
                TdsTopAppBar(
                        titleRes = R.string.empty_string,
                        navigationIcon = ChevronLeft,
                        actionIcon = MoreVert,
                        navigationIconContentDescription = "Back",
                        actionIconContentDescription = "More Options",
                        onNavigationClick = {navController.popBackStack()},
                        onActionClick = {/*TODO Navigate somewhere*/ }
                        )
                     },
                    containerColor = Color.Transparent,
                    content = { innerPadding ->
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(64.dp))
                            GlideImage(
                                model = friend.profilePictureUrl,
                                contentDescription = "User Avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.White, CircleShape)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = friend.fullName,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextGrey
                            )
                            Text(
                                text = friend.nickName,
                                fontSize = 16.sp,
                                color = LightGrey
                            )
                            Text(
                                text = stringResource(R.string.yrs, friend.age),
                                fontSize = 16.sp,
                                color = LightGrey
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.profile_long_text, friend.fullName),
                                fontSize = 14.sp,
                                color = TextGrey,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 32.dp)
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                            Image(
                                painter = graphic,
                                contentDescription = "graphic"
                            )
                        }
                    }
                }
        )
    } ?: run {
        Text(text = stringResource(R.string.loading_friend_details), style = MaterialTheme.typography.bodyMedium)
    }
}