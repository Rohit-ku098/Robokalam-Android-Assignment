package com.example.robokalam.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.robokalam.view.screens.AboutPage
import com.example.robokalam.view.screens.HomeScreen
import com.example.robokalam.view.screens.LoginSignupScreen
import com.example.robokalam.view.screens.SplashScreen
import com.example.robokalam.view.theme.RobokalamTheme
import com.example.robokalam.viewModel.AuthViewModel
import com.example.robokalam.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RobokalamTheme {
                App()
            }
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val mainViewModel: MainViewModel = viewModel()

    val authStatus  by authViewModel.authStatus.collectAsState()
    LaunchedEffect(Unit) {
        authViewModel.getCurrentUser()
    }


    NavHost(
        navController = navController,
        startDestination = Routes.Splash,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {

        composable<Routes.Splash> {
            SplashScreen {
                if (authStatus) {
                    navController.popBackStack()
                    navController.navigate(Routes.Home)
                }
                else {
                    navController.popBackStack()
                    navController.navigate(Routes.LoginSignup)
                }
            }
        }

        composable<Routes.LoginSignup> {
            LoginSignupScreen(
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.Home)
                },
            )
        }

        composable<Routes.Home> {
            HomeScreen(
                authViewModel = authViewModel,
                mainViewModel = mainViewModel,
                onLogout = {
                    authViewModel.logout()
                    navController.popBackStack()
                    navController.navigate(Routes.LoginSignup)
                },
                navigateToAbout = {
                    navController.navigate(Routes.AboutPage)
                }
            )
        }

        composable<Routes.AboutPage> {
            AboutPage(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }


}