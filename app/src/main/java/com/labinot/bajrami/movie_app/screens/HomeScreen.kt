package com.labinot.bajrami.movie_app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.labinot.bajrami.movie_app.R
import com.labinot.bajrami.movie_app.model.Movie
import com.labinot.bajrami.movie_app.model.getMovies
import com.labinot.bajrami.movie_app.navigation.MovieScreens
import com.labinot.bajrami.movie_app.utils.AppColor
import com.labinot.bajrami.movie_app.widget.MovieRow
import com.labinot.bajrami.movie_app.widget.mTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            mTopAppBar(
                title = stringResource(id = R.string.app_name),
                imgVector = painterResource(id = R.drawable.ic_movie),
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = AppColor.primary
    ) { myPading ->



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(myPading),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalItemSpacing = 2.dp) {

                    items(items = getMovies()) { movie ->

                        MovieRow(movie = movie, ){ movie_id ->

                            navController.navigate(MovieScreens.DetailScreen.name + "/$movie_id")
                        }

                    }

                }

        }


    }


}



