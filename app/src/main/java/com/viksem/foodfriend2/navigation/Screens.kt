package com.viksem.foodfriend2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String) {
    // --- Bottom Tabs ---
    data object Home : Screens("home")
    data object CreateNewRecipe : Screens("create_recipe")
    data object Cart : Screens("cart")
    data object Account : Screens("account")

    // --- Secondary Screens ---
    data object RecipeDetails : Screens("recipe_details/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe_details/$recipeId"
    }
    data object Settings : Screens("settings")
    data object Search : Screens("search")
    data object Splash: Screens("splash")
}

data class BottomNavItem(
    val screen: Screens,
    val title: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Screens.Home, "Home", Icons.Default.Home),
    BottomNavItem(Screens.CreateNewRecipe, "Create", Icons.Default.Add),
    BottomNavItem(Screens.Cart, "Cart", Icons.Default.ShoppingCart),
    BottomNavItem(Screens.Account, "Account", Icons.Default.Person)
)