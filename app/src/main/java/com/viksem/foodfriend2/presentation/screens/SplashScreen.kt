package com.viksem.foodfriend2.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.viksem.foodfriend2.R
import com.viksem.foodfriend2.ui.theme.Green25
import com.viksem.foodfriend2.ui.theme.Green900
import com.viksem.foodfriend2.ui.theme.SetStatusBarColor
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Preview
@Composable
fun SplashScreen(
    navController: NavController? = null
) {
    SetStatusBarColor(color = Green25, darkIcons = true)

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val screenWidthPx = with(LocalDensity.current) { screenWidth.toPx() }
    val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() }

    val titleAlpha = remember { Animatable(0f) }

    val leftOffsetX = remember { Animatable(-screenWidthPx) }
    val rightOffsetX = remember { Animatable(screenWidthPx) }
    val bottomOffsetY = remember { Animatable(screenHeightPx) }

    // для карточки
    val cardOffsetY = remember { Animatable(screenHeightPx) }
    var showAuthCard by remember { mutableStateOf(false) }

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
        val isUserAuthorized = true // TODO: замінити на реальну перевірку

        if (isUserAuthorized) {
            navController?.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            showAuthCard = true
            cardOffsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Green25),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Food\nFriend",
            textAlign = TextAlign.Center,
            lineHeight = 56.sp,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily(Font(R.font.cormorant_garamond)),
            fontWeight = FontWeight.Bold,
            fontSize = 64.sp,
            color = Green900,
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
                    .fillMaxSize(0.6f)
                    .align(Alignment.BottomStart)
                    .offset { IntOffset(leftOffsetX.value.roundToInt(), 0) }
            )

            Image(
                painter = painterResource(id = R.drawable.splash_element_right),
                contentDescription = "Right fragment",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .width(screenWidth * 0.7f)
                    .height(screenHeight * 0.5f)
                    .align(Alignment.BottomEnd)
                    .offset { IntOffset(rightOffsetX.value.roundToInt(), 0) },

                )
            Image(
                painter = painterResource(id = R.drawable.splash_element_bottom),
                contentDescription = "Bottom fragment",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(screenWidth * 0.8f)
                    .height(screenHeight * 0.4f)
                    .align(Alignment.BottomStart)
                    .offset { IntOffset(0, bottomOffsetY.value.roundToInt()) }
            )
            if (showAuthCard) {
                WelcomeCard(
//                    onSignUpClick = { /* TODO: Sign up flow */ },
//                    onLoginClick = { /* TODO: Login flow */ },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset { IntOffset(0, cardOffsetY.value.roundToInt()) }
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun WelcomeCard(
//    onSignUpClick: () -> Unit,
//    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Join the Food Friend\nCommunity!",
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = FontFamily(Font(R.font.cormorant_garamond)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {}, //onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sign up with email",
                    fontFamily = FontFamily(Font(R.font.mulish)),)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(
                    text = "Already have an account? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    fontFamily = FontFamily(Font(R.font.mulish)),
                )
                Text(
                    text = "Log in",
                    fontFamily = FontFamily(Font(R.font.mulish)),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.clickable {
                        //onLoginClick()
                    }
                )
            }
        }
    }
}


