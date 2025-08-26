package com.viksem.foodfriend2.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.viksem.foodfriend2.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun SplashScreen(
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = Color(0xFF092727),
            darkIcons = useDarkIcons
        )
    }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }
    val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() }

    val titleAlpha = remember { Animatable(0f) }

    val leftOffsetX = remember { Animatable(-screenWidthPx) }
    val rightOffsetX = remember { Animatable(screenWidthPx) }
    val bottomOffsetY = remember { Animatable(screenHeightPx) }

    LaunchedEffect(Unit) {

        titleAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        )

        leftOffsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        rightOffsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        bottomOffsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )

        delay(1000)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF092727)),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Food\nFriend",
            textAlign = TextAlign.Center,
            lineHeight = 56.sp,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily(Font(R.font.viaoda_libre)),
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp,
            color = Color.White,
            modifier = Modifier
                .graphicsLayer { alpha = titleAlpha.value }
                .padding(top = screenHeight * 0.2f)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.splash_element_left),
                contentDescription = "Left fragment",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize(0.4f)
                    .align(Alignment.BottomStart)
                    .offset { IntOffset(leftOffsetX.value.roundToInt(), 0) }
            )

            Image(
                painter = painterResource(id = R.drawable.splash_element_right),
                contentDescription = "Right fragment",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .width(screenWidth * 0.6f)
                    .height(screenHeight * 0.4f)
                    .align(Alignment.BottomEnd)
                    .offset { IntOffset(rightOffsetX.value.roundToInt(), 0) },

                )
            Image(
                painter = painterResource(id = R.drawable.splash_element_bottom),
                contentDescription = "Bottom fragment",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(screenWidth * 0.6f)
                    .height(screenHeight * 0.3f)
                    .align(Alignment.BottomStart)
                    .offset { IntOffset(0, bottomOffsetY.value.roundToInt()) }
            )
        }
    }
}

