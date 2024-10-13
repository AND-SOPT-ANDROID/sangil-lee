package com.sopt.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sopt.presentation.ui.screen.signin.composable.SignInScreen

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
                SignInScreen(modifier = Modifier.fillMaxSize())
            }
            composable<Routes.Auth.SignUp> {

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