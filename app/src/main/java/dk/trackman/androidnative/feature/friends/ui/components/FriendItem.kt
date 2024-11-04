package dk.trackman.androidnative.feature.friends.ui.components

import androidx.compose.foundation.background
import dk.trackman.androidnative.designsystem.theme.LightGrey
import dk.trackman.androidnative.designsystem.theme.TextGrey
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dk.trackman.androidnative.designsystem.icon.AppIcons.ChevronRight
import dk.trackman.androidnative.designsystem.theme.SecondaryGrey
import dk.trackman.androidnative.feature.friends.ui.models.FriendUI

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FriendItem(friend: FriendUI, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        GlideImage(
            model = friend.profilePictureUrl,
            contentDescription = "Friend Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = friend.fullName, color = TextGrey, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(text = friend.nickName, fontSize = 14.sp, color = LightGrey)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ChevronRight,
            contentDescription = "Arrow Icon",
            tint = LightGrey
        )
    }
    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(SecondaryGrey))
}