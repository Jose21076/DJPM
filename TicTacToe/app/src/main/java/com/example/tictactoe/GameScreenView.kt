package com.example.tictactoe

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

@Composable
fun GameScreenView () {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val density = configuration.densityDpi / 160f
    val screenWidthPx = screenWidth * density
    val screenHeightPx = screenHeight * density

    AndroidView(factory = { context ->
        GameView(context = context,
            width = screenWidthPx.toInt(),
            height = screenHeightPx.toInt() )
    }
    ) {
        it.resume()
    }
}