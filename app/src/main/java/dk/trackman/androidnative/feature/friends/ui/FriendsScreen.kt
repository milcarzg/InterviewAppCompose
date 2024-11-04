package dk.trackman.androidnative.feature.friends.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dk.trackman.androidnative.designsystem.theme.AndroidCodingChallengeTheme
import dk.trackman.androidnative.designsystem.theme.SecondaryGrey
import dk.trackman.androidnative.designsystem.theme.Typography
import dk.trackman.androidnative.feature.friends.ui.components.FriendItem
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI

@Composable
fun FriendsScreenRoute()
{
    AndroidCodingChallengeTheme {
        Surface {
            FriendsScreenContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun FriendsScreenContent(viewModel: FriendsViewModel = hiltViewModel()) {
    viewModel.fetchFriends()
    val friends by viewModel.friends.observeAsState(initial = emptyList())
    val (friendsList, othersList) = friends.partition { it.isFriend }
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Friends",
                            style = Typography.headlineMedium
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp)
            ) {
                SearchBar(
                    query = searchQuery.value,
                    onQueryChanged = { searchQuery.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        SectionTitle(title= "Recently Played")
                    }
                    items(othersList.filter { it.fullName.contains(searchQuery.value, ignoreCase = true) }) { friend ->
                        FriendItem(friend = friend, onClick = {
                            // Handle friend click if needed
                        })
                    }
                    item {
                        SectionTitle(title= "Friends")
                    }
                    items(friendsList.filter { it.fullName.contains(searchQuery.value, ignoreCase = true) }) { friend ->
                        FriendItem(friend = friend, onClick = {
                            // Handle friend click if needed
                        })
                    }
                }
            }
        }
    )
}


@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier
            .background(SecondaryGrey, MaterialTheme.shapes.medium)
            .padding(vertical = 8.dp)
            .height(48.dp)
            .fillMaxWidth(),
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (query.isEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Search by name, email or username",
                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                        )
                    }
                }
                innerTextField()
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            // Handle search action if needed
        })
    )
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



