package dk.trackman.androidnative.feature.friends.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dk.trackman.androidnative.feature.friends.bl.mapper.toFriendUI
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

    private val _friends = MutableLiveData<List<FriendUI>>()
    val friends: LiveData<List<FriendUI>> get() = _friends

    fun getFriendByNickname(nickname: String?): FriendUI? {
        println("find: " + nickname)
        return _friends.value?.find { it.nickName.equals(nickname, ignoreCase = true)
        }
    }

    fun fetchFriends() {
        viewModelScope.launch {
            try {
                // Fetching friends from repository
                val friendList: List<Friend> = friendsRepository.getFriends()

                // Transforming to FriendUI using the mapper extension function
                val friendUIList = friendList.map { it.toFriendUI() }

                // Posting transformed list to LiveData
                _friends.postValue(friendUIList)

            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}