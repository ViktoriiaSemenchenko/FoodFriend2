package com.viksem.foodfriend2.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.viksem.foodfriend2.R
import com.viksem.foodfriend2.ui.theme.Gray300
import com.viksem.foodfriend2.ui.theme.Gray400
import com.viksem.foodfriend2.ui.theme.Gray600
import com.viksem.foodfriend2.ui.theme.SetStatusBarColor
import kotlinx.coroutines.delay

@Preview
@Composable
fun HomeScreen(navController: NavController? = null) {
    SetStatusBarColor(color = Color.White, darkIcons = true)

    Scaffold(Modifier.padding(horizontal = 16.dp)) { innerPadding ->
        Column(
            Modifier.padding(
                innerPadding
            ).fillMaxHeight()
        ) {
            Text(
                "Welcome to Your\nCulinary Hub",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily(Font(R.font.cormorant_garamond)),
                lineHeight = 36.sp,
                fontWeight = FontWeight(600),
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            HomeSearch({ navController?.navigate("home") })
        }
    }
}

@Composable
fun HomeSearch(onClick: () -> Unit) {
    var visibleText by remember { mutableStateOf("") }
    val text = "Find the perfect dish"

    LaunchedEffect(text) {
        while (true) {
            for (i in text.indices) {
                visibleText = text.substring(0, i + 1)
                delay(50L)
            }

            delay(3000L)

            for (i in text.length downTo 0) {
                visibleText = text.substring(0, i)
                delay(50L / 2)
            }

            delay(50L)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClick() }
            .padding(top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Gray600
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = visibleText,
                color = Gray400,
                fontFamily = FontFamily(Font(R.font.mulish)),
            )
        }

        Spacer(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(1.dp)
                .background(Gray300)
        )
    }

}