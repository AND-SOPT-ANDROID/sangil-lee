package com.sopt.presentation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import com.sopt.presentation.ui.component.bottom.WavveBottomBar
import com.sopt.presentation.ui.component.bottom.WavveBottomBarItem
import com.sopt.presentation.ui.screen.home.composable.HomeScreen
import com.sopt.presentation.ui.screen.my.composable.MyScreen
import com.sopt.presentation.ui.screen.signin.composable.SignInScreen
import com.sopt.presentation.ui.screen.signup.composable.SignUpScreen
import com.sopt.presentation.util.getSerialName
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
@Composable
fun WavveNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    var selectedMainBottomTab by remember { mutableStateOf(WavveBottomBarItem.Home) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember { derivedStateOf { navBackStackEntry?.destination?.route } }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (navBackStackEntry?.shouldShowBottomBar() == true)
                WavveBottomBar(
                    onTabSelected = { selectedMainBottomTab = it },
                    selectedTab = selectedMainBottomTab
                )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = Routes.Auth.Graph
        ) {
            navigation<Routes.Auth.Graph>(
                startDestination = Routes.Auth.SignIn
            ) {
                composable<Routes.Auth.SignIn> {
                    SignInScreen(
                        modifier = Modifier.fillMaxSize(),
                        onNavigateToSignUp = { navController.navigate(Routes.Auth.SignUp) },
                        onSignInComplete = { navController.navigate(Routes.Main.Graph) {
                            popUpTo(Routes.Auth.SignIn) { inclusive = true }
                        } }
                    )
                }
                composable<Routes.Auth.SignUp> {
                    SignUpScreen(
                        modifier = Modifier.fillMaxSize(),
                        onActionIconClicked = { navController.popBackStack() },
                        onSignUpComplete = {
                            navController.navigate(Routes.Auth.SignIn) {
                                popUpTo(Routes.Auth.SignIn) { inclusive = false }
                            }
                        }
                    )
                }
            }

            navigation<Routes.Main.Graph>(
                startDestination = Routes.Main.Home
            ) {
                composable<Routes.Main.Home> {
                    HomeScreen(
                        modifier = Modifier
                            .padding(bottom = innerPadding.calculateBottomPadding())
                            .fillMaxSize()
                    )
                }
                composable<Routes.Main.Search> {

                }
                composable<Routes.Main.My> {
                    MyScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = selectedMainBottomTab) {
        if (navBackStackEntry?.shouldShowBottomBar() == true) {
            val targetRoute = when (selectedMainBottomTab) {
                WavveBottomBarItem.Home -> Routes.Main.Home.serializer().getSerialName()
                WavveBottomBarItem.Search -> Routes.Main.Search.serializer().getSerialName()
                WavveBottomBarItem.My -> Routes.Main.My.serializer().getSerialName()
            }
            if (currentRoute != targetRoute) {
                navController.navigate(targetRoute) {
                    popUpTo(Routes.Main.Home) { inclusive = false }
                }
            }
        }
    }

    LaunchedEffect(key1 = currentRoute) {
        selectedMainBottomTab = when (currentRoute) {
            Routes.Main.Home.serializer().getSerialName() -> WavveBottomBarItem.Home
            Routes.Main.Search.serializer().getSerialName() -> WavveBottomBarItem.Search
            Routes.Main.My.serializer().getSerialName() -> WavveBottomBarItem.My
            else -> selectedMainBottomTab
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
private fun NavBackStackEntry.shouldShowBottomBar(): Boolean {
    return this.destination.route in listOf(
        Routes.Main.Home.serializer().getSerialName(),
        Routes.Main.Search.serializer().getSerialName(),
        Routes.Main.My.serializer().getSerialName()
    )
}
