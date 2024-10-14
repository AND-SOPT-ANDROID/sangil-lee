package com.sopt.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sopt.presentation.ui.screen.signin.composable.SignInScreen
import com.sopt.presentation.ui.screen.signup.composable.SignUpScreen

@Composable
fun WavveNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.Auth.Graph
    ) {
        navigation<Routes.Auth.Graph>(
            startDestination = Routes.Auth.SignIn
        ) {
            composable<Routes.Auth.SignIn> {
                SignInScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNavigateToSignUp = { navController.navigate(Routes.Auth.SignUp) }
                )
            }
            composable<Routes.Auth.SignUp> {
                SignUpScreen(
                    modifier = Modifier.fillMaxSize(),
                    onActionIconClicked = { navController.popBackStack() },
                    onSignUpComplete = {
                        navController.navigate(Routes.Auth.SignIn) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        }
                    }
                )
            }
        }

        navigation<Routes.Main.Graph>(
            startDestination = Routes.Main.Home
        ) {
            composable<Routes.Main.Home> {

            }
            composable<Routes.Main.Search> {

            }
            composable<Routes.Main.My> {

            }
        }
    }
}