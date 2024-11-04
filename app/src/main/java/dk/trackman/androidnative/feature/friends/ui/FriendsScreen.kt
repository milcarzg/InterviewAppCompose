package dk.trackman.androidnative.feature.friends.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import dk.trackman.androidnative.R
import dk.trackman.androidnative.designsystem.theme.SecondaryGrey
import dk.trackman.androidnative.feature.friends.ui.components.FriendItem
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI

@Composable
fun FriendsScreenRoute()
{
    FriendsScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun FriendsScreenContent(viewModel: FriendsViewModel = hiltViewModel()) {
    viewModel.fetchFriends()
    val friends by viewModel.friends.observeAsState(initial = emptyList())
    //val friends by viewModel.friends
    //println("Friends: $friends")


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                SectionTitle(title = "Recently Played")
                FriendList(friends = friends.filter { !it.isFriend }, onClick = {})
            }
            item {
                SectionTitle(title = "Friends")
                FriendList(friends = friends.filter { it.isFriend }, onClick = {})
            }
        }
}

@Composable
fun SectionTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier.padding(vertical = 8.dp)
    )
}


@Composable
fun FriendList(friends: List<FriendUI>, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier) {
        friends.forEachIndexed { index, friend ->
            FriendItem(friend = friend, onClick = { onClick() })
            if (index < friends.size - 1) {
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(SecondaryGrey))
            }
        }
    }
}
