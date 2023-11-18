package com.labinot.bajrami.movie_app.navigation

enum class MovieScreens {

    HomeScreen,
    DetailScreen;

    companion object{

        fun fromRoute(route: String?): MovieScreens
        = when (route?.substringBefore("/")) {

            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen

            else -> throw IllegalArgumentException( "Route $route is not recognise")
        }
    }


}