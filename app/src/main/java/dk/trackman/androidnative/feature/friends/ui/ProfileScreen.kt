package dk.trackman.androidnative.feature.friends.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI

@Composable
fun ProfileScreenRoute(nickname: String)
{
    ProfileScreenContent(nickname = nickname)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreenContent(nickname: String, viewModel: ProfileViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    viewModel.fetchFriendByNickname(nickname)
    // Collect the friend state
    val friend by viewModel.friend.collectAsState()
    friend?.let {
        println("ProfileScreenRoute for user: " + friend?.nickName)
        println("Screen for user: ${it.nickName}")

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(listOf(Color(0xFFFFA726), Color.White)))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(64.dp))
                GlideImage(
                    model = it.profilePictureUrl,
                    contentDescription = "User Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.fullName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = it.nickName,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${it.age} yrs",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "You can play recorded Virtual Golf rounds with ${it.fullName} in TrackMan Performance Studio (TPS). More Friends functionalities will be available soon.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatsCard(
                        title = "AVG SCORES BY PAR",
                        value = "3.9  4.1  4.9",
                        modifier = Modifier.weight(1f)
                    )
                    StatsCard(
                        title = "FAIRWAYS HIT",
                        value = "58%",
                        modifier = Modifier.weight(1f)
                    )
                    StatsCard(
                        title = "LONGEST DRIVE",
                        value = "198.5m",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    } ?: run {
        // Loading or error state can be handled here
        Text(text = "Loading friend details...", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun StatsCard(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}