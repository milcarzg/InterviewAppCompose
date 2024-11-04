package dk.trackman.androidnative.network.model

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

