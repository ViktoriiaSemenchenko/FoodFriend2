package com.viksem.foodfriend2.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.viksem.foodfriend2.presentation.components.BottomBar
import com.viksem.foodfriend2.presentation.screens.AccountScreen
import com.viksem.foodfriend2.presentation.screens.CreateRecipeScreen
import com.viksem.foodfriend2.presentation.screens.HomeScreen
import com.viksem.foodfriend2.presentation.screens.RecipeDetailsScreen
import com.viksem.foodfriend2.presentation.screens.ShoppingCartScreen
import com.viksem.foodfriend2.presentation.screens.SplashScreen
import com.viksem.foodfriend2.ui.theme.Gray100

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("home", "cart", "account")) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Gray100)
                    )
                    BottomBar(navController)
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Tabs
            composable(Screens.Splash.route) {
                SplashScreen(
                    navController
                )
            }
            composable(Screens.Home.route) {
                HomeScreen(
                    navController
                )
            }
            composable(Screens.CreateNewRecipe.route) { CreateRecipeScreen(navController) }
            composable(Screens.Cart.route) { ShoppingCartScreen(navController) }
            composable(Screens.Account.route) {
                AccountScreen(
                    navController
                )
            }

            // Secondary
            composable(
                route = Screens.RecipeDetails.route,
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId")
                RecipeDetailsScreen(//recipeId = recipeId,
                    navController
                )

            }
        }
    }
}