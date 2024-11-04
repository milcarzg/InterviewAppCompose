package dk.trackman.androidnative.feature.friends.data.repository

import dk.trackman.androidnative.feature.friends.data.api.FriendApi
import dk.trackman.androidnative.feature.friends.data.model.Friend
import javax.inject.Inject

class FriendsRepository @Inject constructor(
    private val friendApi: FriendApi
) {
    suspend fun getFriends(): List<Friend> {
        // Make a network call using Retrofit and return the list of friends
        return friendApi.getFriends().friends
    }
}