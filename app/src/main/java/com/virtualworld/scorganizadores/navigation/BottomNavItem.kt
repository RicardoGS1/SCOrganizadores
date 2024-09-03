package com.virtualworld.scorganizadores.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    object Home : BottomNavItem(
        title = "Catalogo",
        icon = Icons.Default.Home,
        route = com.virtualworld.scorganizadores.navigation.Home.route,
    )

    object Cart : BottomNavItem(
        title = "Ordenes",
        icon = Icons.Default.ShoppingCart,
        route = com.virtualworld.scorganizadores.navigation.Cart.route,
    )

    object Favorite : BottomNavItem(
        title = "Favorito",
        icon = Icons.Default.Favorite,
        route = com.virtualworld.scorganizadores.navigation.Favorite.route,
    )

    object Profile : BottomNavItem(
        title = "Usuario",
        icon = Icons.Default.AccountCircle,
        route = com.virtualworld.scorganizadores.navigation.Profile.route,
    )
}
