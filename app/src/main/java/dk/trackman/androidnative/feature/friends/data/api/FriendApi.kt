package dk.trackman.androidnative.feature.friends.data.api

import dk.trackman.androidnative.feature.friends.data.models.FriendsResponse
import retrofit2.http.GET

interface FriendApi {
    @GET("friends.json")
    suspend fun getFriends(): FriendsResponse
}