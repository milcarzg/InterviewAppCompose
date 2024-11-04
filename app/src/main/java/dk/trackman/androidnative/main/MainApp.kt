package dk.trackman.androidnative.main

import androidx.compose.runtime.Composable
import dk.trackman.androidnative.designsystem.component.TdsBackground
import dk.trackman.androidnative.navigation.MainAppNavHost

@Composable
fun MainApp(
    appState: MainAppState = rememberMainAppState(),
) {
    TdsBackground {
        MainAppNavHost(
            appState = appState,
        )
    }
}
