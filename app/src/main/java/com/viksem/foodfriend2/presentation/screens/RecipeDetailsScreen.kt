package com.viksem.foodfriend2.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.viksem.foodfriend2.presentation.components.ScreenContent

@Composable
fun RecipeDetailsScreen(//recipeId: String,
                        navController: NavController) {
    ScreenContent(
        title = "Recipe Details Screen",
        //subtitle = "Recipe ID: $recipeId"
    )
}