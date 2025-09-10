package com.viksem.foodfriend2.navigation

sealed class Screens(val route: String) {
    // Bottom Tabs
    data object Home : Screens("home")
    data object CreateNewRecipe : Screens("create_recipe")
    data object Cart : Screens("cart")
    data object Account : Screens("account")

    // Secondary Screens
    data object RecipeDetails : Screens("recipe_details/{recipeId}") {
        fun createRoute(recipeId: String) = "recipe_details/$recipeId"
    }

    data object Settings : Screens("settings")
    data object Search : Screens("search")
    data object Splash : Screens("splash")
}