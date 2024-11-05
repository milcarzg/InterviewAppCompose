package dk.trackman.androidnative.feature.friends.domain

import dk.trackman.androidnative.feature.friends.data.models.Friend
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

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
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val birthDate = OffsetDateTime.parse(dateOfBirth, formatter).toLocalDate()
    val currentDate = LocalDate.now()
    val age = Period.between(birthDate, currentDate).years
    return age.toString() // Placeholder
}