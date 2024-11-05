package dk.trackman.androidnative.feature.friends.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.trackman.androidnative.feature.friends.domain.toFriendUI
import dk.trackman.androidnative.feature.friends.data.model.Friend
import dk.trackman.androidnative.feature.friends.data.repository.FriendsRepository
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI
import kotlinx.coroutines.launch
import javax.inject.Inject

// HiltViewModel to handle friends data loading
@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendsRepository: FriendsRepository
) : ViewModel() {

    private val _friends = mutableStateOf<List<FriendUI>>(emptyList())
    val friends: State<List<FriendUI>> get() = _friends

    init {
        println("FriendsViewModel initialized")
        fetchFriends()
    }

    private fun fetchFriends() {
        viewModelScope.launch {
            try {
                // Fetching friends from repository
                val friendList: List<Friend> = friendsRepository.getFriends()

                // Transforming to FriendUI using the mapper extension function
                _friends.value = friendList.map { it.toFriendUI() }

            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}