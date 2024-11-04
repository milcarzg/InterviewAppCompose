package dk.trackman.androidnative.feature.friends.ui.models

// UI Model for a single friend
data class FriendUI(
    val fullName: String,
    val nickName: String,
    val age: String,
    val profilePictureUrl: String,
    val isFriend: Boolean,
    val majorsWon: Int
)


// possibly a companion obejct here instead of FriendMapper
/*{
    companion object {
        fun fromFriend(friend: Friend): FriendUI {
            val fullName = "${friend.firstName} ${friend.lastName}"
            val age = calculateAge(friend.dateOfBirth)
            return FriendUI(
                fullName = fullName,
                nickName = friend.nickName,
                age = age,
                profilePictureUrl = friend.profilePictureUrl,
                isFriend = friend.isFriend,
                majorsWon = friend.majorsWon
            )
        }

        private fun calculateAge(dateOfBirth: String): String {
            // Your implementation for calculating age
            return "35 years old" // Placeholder
        }
    }
}*/
