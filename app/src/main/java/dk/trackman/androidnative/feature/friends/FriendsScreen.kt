package dk.trackman.androidnative.feature.friends

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dk.trackman.androidnative.R
import dk.trackman.androidnative.designsystem.theme.AndroidCodingChallengeTheme
import dk.trackman.androidnative.designsystem.theme.ThemePreviews

@Composable
fun FriendsScreenRoute(
){
    FriendsScreenContent()
}

@Composable
private fun FriendsScreenContent(viewModel: FriendsViewModel = hiltViewModel())
{
    val friends by viewModel.friends
    println("Friends: $friends")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.dummy_text).uppercase(),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@ThemePreviews
@Composable
fun FriendsScreenContentPreview() {
    AndroidCodingChallengeTheme {
        FriendsScreenContent()
    }
}
