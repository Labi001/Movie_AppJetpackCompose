package com.labinot.bajrami.movie_app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.labinot.bajrami.movie_app.navigation.MovieNavigation
import com.labinot.bajrami.movie_app.ui.theme.Movie_AppTheme
import com.labinot.bajrami.movie_app.utils.AppColor


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Movie_AppTheme {

                MyApp {

                    MovieNavigation()

                }

            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit={}) {
    val systemUiController = rememberSystemUiController()
    val isSystemInDarkMode = isSystemInDarkTheme()


    SideEffect {

        systemUiController.setSystemBarsColor(

            color = AppColor.primaryDark,
            darkIcons = isSystemInDarkMode
        )

    }

    content()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    Movie_AppTheme {
        MyApp {

            MovieNavigation()

        }
    }
}