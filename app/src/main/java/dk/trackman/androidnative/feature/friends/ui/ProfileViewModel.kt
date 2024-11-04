package dk.trackman.androidnative.feature.friends.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dk.trackman.androidnative.feature.friends.bl.mapper.toFriendUI
import dk.trackman.androidnative.feature.friends.data.repository.FriendsRepository
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val friendsRepository: FriendsRepository) : ViewModel() {

    private val _friend = MutableStateFlow<FriendUI?>(null)
    val friend: StateFlow<FriendUI?> = _friend.asStateFlow()

    // Fetch friend by nickname
    fun fetchFriendByNickname(nickname: String) {
        viewModelScope.launch {
            val foundFriend = friendsRepository.getFriend(nickname)
            _friend.value = foundFriend?.toFriendUI()
        }
    }
}