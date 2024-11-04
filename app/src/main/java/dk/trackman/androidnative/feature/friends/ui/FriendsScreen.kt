package dk.trackman.androidnative.feature.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dk.trackman.androidnative.designsystem.theme.SecondaryGrey
import dk.trackman.androidnative.feature.friends.data.model.Friend
import dk.trackman.androidnative.feature.friends.ui.FriendsViewModel
import dk.trackman.androidnative.feature.friends.ui.components.FriendItem

@Composable
fun FriendsScreenRoute()
{
    FriendsScreenContent()
}

@Composable
private fun FriendsScreenContent(viewModel: FriendsViewModel = hiltViewModel()) {
    val friends by viewModel.friends
    println("Friends: $friends")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "FRIENDS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //SearchBar(modifier = Modifier.fillMaxWidth())

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
fun FriendList(friends: List<Friend>, modifier: Modifier = Modifier, onClick: (Friend) -> Unit) {
    Column(modifier = modifier) {
        friends.forEachIndexed { index, friend ->
            FriendItem(friend = friend, onClick = { onClick(friend) })
            if (index < friends.size - 1) {
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(SecondaryGrey))
            }
        }
    }
}
