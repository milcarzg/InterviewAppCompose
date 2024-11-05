package dk.trackman.androidnative.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

//Example of helper classes that might be needed for your project
class ImageHelper {

    companion object {

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
    }
}