@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.labinot.bajrami.movie_app.widget

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.labinot.bajrami.movie_app.model.Movie
import com.labinot.bajrami.movie_app.model.getMovies
import com.labinot.bajrami.movie_app.utils.AppColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mTopAppBar(title:String,
               imgVector:Painter,
               scrollBehavior: TopAppBarScrollBehavior){

    TopAppBar(title = {

        Text(text = title,
            color = Color.White,
            fontSize = 18.sp
          )
    },
        scrollBehavior = scrollBehavior,
        navigationIcon = {

            Icon(modifier = Modifier
                .size(40.dp)
                .padding(start = 10.dp, end = 5.dp),
                painter = imgVector,
                contentDescription = "App Icon",
                tint = Color.White)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = AppColor.primaryDark
        ))

}

@Preview(showBackground = true)
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit ={}) {

    val context = LocalContext.current
    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(10.dp)
            .clickable {

                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(13.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = AppColor.primaryLight
        )
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
          verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {

            Surface(modifier = Modifier
                .clip(RoundedCornerShape(topStart = 13.dp, topEnd = 13.dp))
                .height(100.dp)
                .fillMaxWidth(),
                color = Color.Transparent,
                shadowElevation = 8.dp) {

              Image(modifier =
              Modifier
                  .fillMaxWidth()
                  .fillMaxHeight(),
                  contentDescription = "Image",
                  painter = rememberAsyncImagePainter(model = movie.images[0],
                      imageLoader = imageLoader,
                      ),
                  contentScale = ContentScale.Crop )

            }

            Text(modifier = Modifier.padding(start = 10.dp),
                text = movie.name,
                maxLines = 1,
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium)

            Text(modifier = Modifier.padding(start = 10.dp),
                text = "Director: ${movie.director}",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodySmall)

            Text(modifier = Modifier.padding(start = 10.dp),
                text = "Release: ${movie.year}",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(4.dp))


            AnimatedVisibility(visible = expanded) {

                Column(modifier = Modifier.padding(start = 10.dp)) {

                    Text(buildAnnotatedString {

                        withStyle(style = SpanStyle(color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            )){

                            append("Plot: ")
                        }

                        withStyle(style = SpanStyle(color = Color.LightGray,
                            fontSize = 13.sp)){

                            append(movie.plot)
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