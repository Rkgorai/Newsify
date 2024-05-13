package com.mobilecomputing.newsapp.screens.locations

import androidx.compose.material3.darkColorScheme

import android.os.Build
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.mobilecomputing.newsapp.R
import com.mobilecomputing.newsapp.utils.Constant.fullAddress
import com.mobilecomputing.newsapp.utils.Constant.state_location
import kotlinx.coroutines.delay

@Composable
fun LocationHome(lat: String, lon: String, viewModel: LocationViewModel = hiltViewModel()) {
    DisplayLocationData(viewModel)
    viewModel.getLocationData(lat, lon)
}
@Composable
fun DisplayLocationData(viewModel: LocationViewModel) {
    // Display the location data

    val showSplashScreen = remember { mutableStateOf(true) }
    val delayFinished = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(10000) // delay for 10 seconds
        delayFinished.value = true
    }

    val resultsfetched = viewModel.data.value.data?.toMutableList()
    AnimatedVisibility(
        visible = showSplashScreen.value && !delayFinished.value,
        enter = fadeIn(animationSpec = tween(1000, easing = LinearOutSlowInEasing)),
        exit = fadeOut(animationSpec = tween(1000))
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GifImage(modifier = Modifier.fillMaxSize()) // Use the GifImage function here
//            Text(
//                text = "Newsify",
//                modifier = Modifier.align(Alignment.TopCenter)
//                    .padding(top = 160.dp),
//                style = MaterialTheme.typography.titleLarge,
//                fontSize = 50.sp,
//                color = Color.Black
//            )
            val targetState = remember { "Newsify" }
            val alpha: Float by animateFloatAsState(
                targetValue = if (targetState == "Newsify") 1f else 0f,
                animationSpec = tween(100, easing = LinearOutSlowInEasing), label = ""
            )

            Text(
                text = targetState,
                modifier = Modifier.align(Alignment.TopCenter)
                    .padding(top = 140.dp)
                    .alpha(alpha),

                style = MaterialTheme.typography.titleLarge,
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )


        }
        Log.d("LOADING", "DisplayLocationData: Loading")
    }

    if (!viewModel.data.value.loading!!) {
        showSplashScreen.value = false
        Log.d("RESULT", "DisplayLocationData: $resultsfetched")
        state_location.value = resultsfetched?.get(0)?.state.toString()
        val locationDataItem = resultsfetched?.get(0)
        fullAddress.value = "${locationDataItem?.name},\nState: ${locationDataItem?.state},\nCountry: ${locationDataItem?.country}"
    }
}
@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()

    // Determine which GIF to display based on the theme
    val gifResource = if (isSystemInDarkTheme()) {

        R.drawable.loading // Replace with your dark theme GIF
    } else {
        R.drawable.loading // Replace with your light theme GIF
    }

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifResource).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}
