package dk.trackman.androidnative.feature.friends.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.trackman.androidnative.feature.friends.bl.mapper.toFriendUI
import dk.trackman.androidnative.feature.friends.data.repository.FriendsRepository
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

// HiltViewModel to handle friends data loading
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val friendsRepository: FriendsRepository
) : ViewModel() {

    private val _friend = MutableStateFlow<FriendUI?>(null)
    val friend: StateFlow<FriendUI?> = _friend.asStateFlow()

    // Fetch a friend by their nickname
    fun fetchFriendByNickname(nickname: String) {
        val friend = friendsRepository.getFriend(nickname)
        _friend.value = friend?.toFriendUI()
    }
}