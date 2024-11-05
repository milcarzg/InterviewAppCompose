package dk.trackman.androidnative.feature.friends.data.models

// Data classes representing a User
data class Friend(
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val dateOfBirth: String,
    val profilePictureUrl: String,
    val isFriend: Boolean,
    val majorsWon: Int
)

