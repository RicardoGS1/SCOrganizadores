package com.virtualworld.scorganizadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.virtualworld.scorganizadores.navigation.AppBottomNavBar
import com.virtualworld.scorganizadores.navigation.AppNavHost
import com.virtualworld.scorganizadores.navigation.SignIn
import com.virtualworld.scorganizadores.navigation.SignUp
import com.virtualworld.scorganizadores.ui.common.OfflineDialog
import com.virtualworld.scorganizadores.ui.common.StoreAppState
import com.virtualworld.scorganizadores.ui.common.rememberStoreAppState

import com.virtualworld.scorganizadores.ui.theme.AppTheme
import com.virtualworld.scorganizadores.ui.screens.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(rememberNavController())
                }

            }
        }
    }
}



@Composable
fun MainScreen(rememberNavController: NavHostController) {

    val cartViewModel: CartViewModel = hiltViewModel()
    val badgeCount by cartViewModel.badgeCount.collectAsState()

    val onBadgeCountChange: (Int) -> Unit = { newBadgeCount ->
        cartViewModel.updateBadgeCount(newBadgeCount)
    }

    val appState: StoreAppState = rememberStoreAppState()
    val modifier: Modifier = Modifier

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        if (!appState.isOnline) {
            OfflineDialog(onRetry = { appState.refreshOnline() })
        } else {
            val navController = rememberNavController()
            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }


            val navBackStackEntry by navController.currentBackStackEntryAsState()


            when (navBackStackEntry?.destination?.route) {
                SignIn.route, SignUp.route ->
                    bottomBarState.value = false

                else ->
                    bottomBarState.value = true
            }


            Scaffold(
                bottomBar = {
                    if (bottomBarState.value) {
                        AppBottomNavBar(
                            navController = navController,
                            bottomBarState = bottomBarState,
                            badgeState = badgeCount,
                        )
                    }
                },
            ) { paddingValues ->
                AppNavHost(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues),
                    onBadgeCountChange = onBadgeCountChange,
                )
            }
        }
    }
}


