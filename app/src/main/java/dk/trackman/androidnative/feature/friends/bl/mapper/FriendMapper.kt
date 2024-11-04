package dk.trackman.androidnative.feature.friends.bl.mapper

import dk.trackman.androidnative.feature.friends.data.model.Friend
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI

fun Friend.toFriendUI(): FriendUI {
    val fullName = "$firstName $lastName"
    // Assuming you have some method to calculate age or format the date
    val age = calculateAge(dateOfBirth)
    return FriendUI(
        fullName = fullName,
        nickName = nickName,
        age = age,
        profilePictureUrl = profilePictureUrl,
        isFriend = isFriend,
        majorsWon = majorsWon
    )
}

fun calculateAge(dateOfBirth: String): String {
    // Implement logic of calculating the age from dateOfBirth
    return "30 years old" // Placeholder
}