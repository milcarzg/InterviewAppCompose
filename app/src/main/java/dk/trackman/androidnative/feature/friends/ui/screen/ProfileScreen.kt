package dk.trackman.androidnative.feature.friends.ui.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dk.trackman.androidnative.R
import dk.trackman.androidnative.feature.friends.ui.viewmodel.FriendsViewModel
import androidx.compose.foundation.Image

@Composable
fun ProfileScreenRoute(nickName: String)
{
    ProfileScreenContent(nickName= nickName)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreenContent(nickName: String, viewModel: FriendsViewModel = hiltViewModel(), modifier: Modifier = Modifier) {

    val friends by viewModel.friends
    val friend = friends.find { it.nickName == nickName }
    val background = painterResource(R.drawable.profile_background)
    val graphic = painterResource(R.drawable.graphic)


    println("Screen for user: $friend")
    friend?.let {
        println("Screen for user: ${it.nickName}")
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(painter = background,
                contentDescription = "Background")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(64.dp))
                GlideImage(
                    model = friend.profilePictureUrl,
                    contentDescription = "User Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = friend.fullName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = friend.nickName,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${friend.age} yrs",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "You can play recorded Virtual Golf rounds with ${friend.fullName} in TrackMan Performance Studio (TPS). More Friends functionalities will be available soon.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Image(painter = graphic,
                    contentDescription = "graphic")

            }
        }
    } ?: run {
        // Loading or error state can be handled here
        Text(text = "Loading friend details...", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun LoadImageFromAssets(fileName: String) {
    val context = LocalContext.current
    val imageBitmap: Bitmap? = remember {
        loadBitmapFromAssets(context, fileName)
    }

    Box(
        modifier = Modifier.size(200.dp)
    ) {
        imageBitmap?.let {
            Image(
                painter = BitmapPainter(it.asImageBitmap()),
                contentDescription = "Image from Assets",
                modifier = Modifier.matchParentSize()
            )
        }
    }
}

fun loadBitmapFromAssets(context: Context, fileName: String): Bitmap? {
    return try {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}




@Composable
fun StatsCard(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}