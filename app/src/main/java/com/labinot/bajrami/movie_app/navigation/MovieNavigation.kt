package com.labinot.bajrami.movie_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.labinot.bajrami.movie_app.screens.DetailScreen
import com.labinot.bajrami.movie_app.screens.HomeScreen

@Composable
fun MovieNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name){
        
        composable(MovieScreens.HomeScreen.name){
            
            HomeScreen(navController = navController)
        }


        val route = MovieScreens.DetailScreen.name
        composable(
            "$route/{movie}",
            arguments = listOf(navArgument(name = "movie"){type = NavType.StringType})
        ){

            navBackStackEntry ->

            DetailScreen(navController = navController,
                navBackStackEntry.arguments?.getString("movie"))
        }
        
    }

}