package com.viksem.foodfriend2.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.viksem.foodfriend2.ui.theme.Green500
import com.viksem.foodfriend2.ui.theme.Green900

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val filledIcon: ImageVector,
    val outlineIcon: ImageVector
) {
    object Home : BottomNavItem("Home", "home", Icons.Filled.Home, Icons.Outlined.Home)
    object Cart :
        BottomNavItem("Cart", "cart", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart)

    object Account : BottomNavItem("Account", "account", Icons.Filled.Person, Icons.Outlined.Person)
    object NewRecipe : BottomNavItem(
        "New Recipe", "create_recipe", Icons.Filled.AddCircle,
        Icons.Outlined.AddCircleOutline
    )
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.NewRecipe,
    BottomNavItem.Cart,
    BottomNavItem.Account,
)

@Preview
@Composable
fun BottomBar(navController: NavController? = null) {
    val currentDestination = navController?.currentBackStackEntryAsState()?.value?.destination

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any {
                it.route == item.route
            } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController?.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) item.filledIcon else item.outlineIcon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Green900,
                    unselectedIconColor = Green500,
                    selectedTextColor = Green900,
                    unselectedTextColor = Green500
                ),
                interactionSource = remember { MutableInteractionSource() },
                alwaysShowLabel = true
            )
        }
    }
}
