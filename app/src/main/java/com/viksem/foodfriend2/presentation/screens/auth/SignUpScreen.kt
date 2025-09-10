package com.viksem.foodfriend2.presentation.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.viksem.foodfriend2.presentation.screens.home.HomeSearch
import com.viksem.foodfriend2.ui.theme.SetStatusBarColor

@Preview
@Composable
fun SignUpScreen(navController: NavController? = null){
    //SetStatusBarColor(color = Color.White, darkIcons = true)

    Scaffold(Modifier.padding(horizontal = 16.dp)) { innerPadding ->
        Column(Modifier.padding(innerPadding.calculateBottomPadding())) {
            Text(
                text = "Sign up",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily(Font(R.font.cormorant_garamond)),
                fontWeight = FontWeight(600),
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
        }
    }
}