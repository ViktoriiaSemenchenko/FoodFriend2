package com.viksem.foodfriend2.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.viksem.foodfriend2.presentation.components.ScreenContent

@Composable
fun HomeScreen(navController: NavController) {
    ScreenContent(
        title = "Home Screen",
        onClick = {
            // Приклад переходу на екран RecipeDetails з аргументом
            navController.navigate("recipe_details/123")
        }
    )
}