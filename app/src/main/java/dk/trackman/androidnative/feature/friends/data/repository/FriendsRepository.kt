package dk.trackman.androidnative.feature.friends.data.repository

import dk.trackman.androidnative.feature.friends.data.api.FriendApi
import dk.trackman.androidnative.feature.friends.data.models.Friend
import javax.inject.Inject

class FriendsRepository @Inject constructor(
    private val friendApi: FriendApi
) {
    // In-memory cache of friends list
    private var friendsCache: List<Friend>? = null

    // Get friends list from network or cache
    suspend fun getFriends(): List<Friend> {
        // Return the cached list if it is available
        if (friendsCache != null) {
            return friendsCache!!
        }

        // Otherwise, make the network call to fetch friends and update the cache
        val fetchedFriends = friendApi.getFriends().friends
        setFriends(fetchedFriends)
        println("Friends Cache: $friendsCache")
        return fetchedFriends
    }

    fun setFriends(fetchedFriends: List<Friend>) {
        friendsCache = fetchedFriends
        println("Friends Cache: $friendsCache")
    }


    // Get a specific friend by nickname
    fun getFriend(nickname: String): Friend? {
        // Make sure to access the cached data to avoid re-fetching from the network
        println("Friends Cache Get: $friendsCache")
        return friendsCache?.find { it.nickName.equals(nickname, ignoreCase = true) }
    }
}