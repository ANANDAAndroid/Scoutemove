package com.clone.scoutemove.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.clone.scoutemove.utils.AppSettings
import com.clone.scoutemove.utils.NetworkObserver
import com.clone.scoutemove.utils.Status

@Composable
fun App(networkObserver: NetworkObserver, dataStore: DataStore<AppSettings>) {
    val navController = rememberNavController()
    val state by networkObserver.observe()
        .collectAsState(initial = if (networkObserver.isActuallyConnected()) Status.AVAILABLE else Status.UNAVAILABLE)
    NavHost(navController = navController, startDestination = "parent") {

        navigation(startDestination = "login", route = "parent", enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(200)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(200)
            )
        }, popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(200)
            )
        },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(200)
                )
            }) {

            composable(route = "login") {
                LoginScreen(dataStore = dataStore, onTextClick = {
                    navController.navigate(route = "sign_up")
                }) {
                    navController.navigate(route = "web_view") {
                        popUpTo("login"){
                            inclusive=true
                        }
                    }
                }
            }

            composable(route = "sign_up") {
                SignUpScreen(dataStore = dataStore){
                    navController.navigate(route = "login")
                }
            }
            composable(route = "web_view") {
                MainScreen(state = state)
            }
        }

    }
}