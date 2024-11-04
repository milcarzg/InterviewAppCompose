package dk.trackman.androidnative.network.api

import dk.trackman.androidnative.network.model.FriendsResponse
import retrofit2.http.GET

interface FriendApi {
    @GET("friends.json")
    suspend fun getFriends(): FriendsResponse
}