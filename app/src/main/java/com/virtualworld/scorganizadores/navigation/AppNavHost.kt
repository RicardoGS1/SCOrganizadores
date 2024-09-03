package com.virtualworld.scorganizadores.navigation // ktlint-disable package-name

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.virtualworld.scorganizadores.ui.screens.auth.SignInRoute
import com.virtualworld.scorganizadores.ui.screens.auth.SignUpRoute
import com.virtualworld.scorganizadores.ui.screens.cart.CartRoute
import com.virtualworld.scorganizadores.ui.screens.detail.DetailRoute
import com.virtualworld.scorganizadores.ui.screens.order.OrderViewModel

import com.virtualworld.scorganizadores.ui.screens.favorite.FavoriteRoute
import com.virtualworld.scorganizadores.ui.screens.home.HomeRoute
import com.virtualworld.scorganizadores.ui.screens.order.PaymentRoute
import com.virtualworld.scorganizadores.ui.screens.profile.ProfileRoute


@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier, onBadgeCountChange: (Int) -> Unit)
{

    NavHost(navController = navController, startDestination = Home.route, modifier = modifier) {


        composable(Home.route) {
            HomeRoute(
                onProductClicked = {
                    val route = "${ProductDetail.route}/${it.id}"
                    navController.navigate(route = route)
                },
            )
        }

        composable(Profile.route) {

            ProfileRoute(loin = {
                navController.navigate(SignIn.route) {
                    //  popUpTo(Home.route) {
                    //       inclusive = false
                    //   }
                }
            }) {
                navController.navigate(SignIn.route) {
                    navController.popBackStack()
                }
            }
        }

        composable(SignIn.route) {
            SignInRoute(
                onGoSignUpButtonClicked = {
                    navController.navigate(SignUp.route)
                },
                navigateToProfileScreen = {
                    navController.navigate(Profile.route) {
                        popUpTo(Home.route)
                    }

                },
            )
        }

        composable(SignUp.route) {
            SignUpRoute(
                navigateToSignInScreen = {
                    navController.navigate(Profile.route)
                },
            )
        }


        //*******************************************************************************************************************


        composable(ProductDetail.routeWithArgs, arguments = ProductDetail.arguments) {
            DetailRoute(
                onBadgeCountChange = onBadgeCountChange,
            )
        }


        composable(Cart.route) {
            CartRoute(
                onClickedBuyNowButton = {
                    navController.navigate(Payment.route)
                },
                onCartClicked = {
                    val route = "${ProductDetail.route}/${it.productId}"
                    navController.navigate(route = route)
                },
                onBadgeCountChange = onBadgeCountChange,
            )
        }




        composable(Favorite.route) {
            FavoriteRoute(
                onProductClicked = {
                    val route = "${ProductDetail.route}/${it.productId}"
                    navController.navigate(route = route)
                },
            )
        }


        composable(Payment.route) {
            val viewModel = hiltViewModel<OrderViewModel>()
            PaymentRoute(viewModel)
        }




    }
}
