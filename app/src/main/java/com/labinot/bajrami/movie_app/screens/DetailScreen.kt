package com.labinot.bajrami.movie_app.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.labinot.bajrami.movie_app.model.Movie
import com.labinot.bajrami.movie_app.model.getMovies
import com.labinot.bajrami.movie_app.utils.AppColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,
                 movieId: String?){

    val newMovieList = getMovies().filter { movie: Movie ->

        movie.id == movieId

    }

    Scaffold(
        topBar = {

                 TopAppBar(title = {

                     Text(text = "Detail Screen",
                         color = Color.White,
                         fontSize = 18.sp,
                         fontWeight = FontWeight.Bold
                     )
                 },
                     navigationIcon = {

                                          Icon(imageVector = Icons.Default.ArrowBack,
                                              contentDescription = "Back Icon",
                                              tint = Color.White,
                                              modifier = Modifier
                                                  .padding(start = 5.dp, end = 7.dp)
                                                  .clickable {

                                                      navController.popBackStack()
                                                  })



                     },
                     colors = TopAppBarDefaults.smallTopAppBarColors(

                         containerColor = AppColor.primaryDark
                     ))

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

            HeaderRow(myMovie = newMovieList)
            Spacer(modifier = Modifier.height(7.dp))
            Text(modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                text = "Movie Images:",
                color = Color.White,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(5.dp))
            ImagesRow(myMovies = newMovieList)


        }


    }

}

@Composable
fun HeaderRow(myMovie: List<Movie>) {

    val context = LocalContext.current
    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
            color = AppColor.primaryLight
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {

                Surface(modifier = Modifier
                    .width(130.dp)
                    .height(180.dp)
                    .padding(7.dp),
                    shape = RoundedCornerShape(6.dp),
                    shadowElevation = 2.dp) {

                    Image(modifier = Modifier.fillMaxSize(),
                        painter = rememberAsyncImagePainter(model = myMovie[0].images[0],
                            imageLoader = imageLoader
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Detail Photo" )

                }

                Column(modifier = Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center) {

                    Text(
                        text = myMovie[0].name,
                        maxLines = 1,
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge)

                    Text(
                        text = "Director: ${myMovie[0].director}",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium)

                    Text(
                        text = "Release: ${myMovie[0].year}",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium)

                    Text(
                        text = "Rating: ${myMovie[0].rating}",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(4.dp))

                    AnimatedVisibility(visible = expanded) {

                        Column() {

                            Text(buildAnnotatedString {

                                withStyle(style = SpanStyle(color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                ){

                                    append("Actors: ")
                                }

                                withStyle(style = SpanStyle(color = Color.LightGray,
                                    fontSize = 13.sp)
                                ){

                                    append(myMovie[0].actors)
                                }

                            })

                            Divider(thickness = 2.dp,
                                color = Color.White)

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(buildAnnotatedString {

                                withStyle(style = SpanStyle(color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                ){

                                    append("Plot: ")
                                }

                                withStyle(style = SpanStyle(color = Color.LightGray,
                                    fontSize = 13.sp)
                                ){

                                    append(myMovie[0].plot)
                                }

                            })

                        }

                    }



                    Image(modifier = Modifier
                        .padding(start = 10.dp)
                        .size(25.dp)
                        .clickable {

                            expanded = !expanded
                        },
                        imageVector = if(expanded) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Arrow ",
                        colorFilter = ColorFilter.tint(
                            color = Color.White
                        ))


                }

            }


        }

    }




}

@Composable
fun ImagesRow(myMovies: List<Movie>) {

    Surface(modifier = Modifier.fillMaxWidth()
        .padding(3.dp),
        shape = RoundedCornerShape(16.dp),
        color = AppColor.primaryLight,
        border = BorderStroke(2.dp, color = AppColor.primaryDark)
        ) {

        LazyRow{

            items(items = myMovies[0].images){ image ->

                ImageSquer(image = image)


            }





        }

    }


}

@Composable
fun ImageSquer(image: String) {

    val context = LocalContext.current
    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }

    Card(modifier = Modifier
        .size(200.dp)
        .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(

            defaultElevation = 3.dp
        ),
        border = BorderStroke(width = 2.dp, color = AppColor.primaryDark)
    ) {

        Image(modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(model = image,
            imageLoader = imageLoader),
            contentScale = ContentScale.Fit,
            contentDescription = "Images List" )

    }

}
