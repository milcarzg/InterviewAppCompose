package dk.trackman.androidnative.feature.friends

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.trackman.androidnative.network.api.FriendApi
import dk.trackman.androidnative.network.model.Friend
import kotlinx.coroutines.launch
import javax.inject.Inject

// HiltViewModel to handle user data loading
@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendApi: FriendApi
) : ViewModel() {

    private val _friends = mutableStateOf<List<Friend>>(emptyList())
    val friends: State<List<Friend>> get() = _friends

    init {
        println("FriendsViewModel initialized")
        loadFriends()
    }

    private fun loadFriends() {
        println("Loading users...")
        viewModelScope.launch {
            println("Fetching users...")
            try {
                val response = friendApi.getFriends()
                println("Response: $response")
                _friends.value = response.friends.map { friendResponse ->
                    Friend(
                        firstName = friendResponse.firstName,
                        lastName = friendResponse.lastName,
                        nickName = friendResponse.nickName,
                        dateOfBirth = friendResponse.dateOfBirth,
                        profilePictureUrl = friendResponse.profilePictureUrl,
                        isFriend = friendResponse.isFriend,
                        majorsWon = friendResponse.majorsWon
                    )
                }
            } catch (e: Exception) {
                println("Error loading friends: ${e.message}")
                // Handle the error appropriately (e.g., show a message to the user)
            }
        }
    }
}