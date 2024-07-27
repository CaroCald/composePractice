package com.example.practicecompose.features.movies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.practicecompose.commons.components.bottomBar.BottomNavigationBar
import com.example.practicecompose.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.commons.components.text.TextCustom
import com.example.practicecompose.commons.components.toolbar.ToolBarCustom
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.constants.Constants
import com.example.practicecompose.data.remote.models.movies.Result
import com.example.practicecompose.navigation.NavigationItem

@Composable
fun MovieScreen(
    navController: NavHostController,
    moviesViewModel: MoviesViewModel = hiltViewModel()
) {
    val moviesState by moviesViewModel.movieState.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<Throwable?>(null) }
    var results by remember { mutableStateOf(emptyList<Result>())}


    LaunchedEffect(Unit){
        moviesViewModel.fetchMovies()
    }

    ScaffoldCustom(
        customToolBar = { ToolBarCustom(navController = navController) },
        customBottomBar = { BottomNavigationBar(navController) },
        customBody = {
            when (moviesState) {
                is ApiResult.Loading -> {
                    isLoading = (moviesState as ApiResult.Loading).isLoading
                }
                is ApiResult.Success -> {
                    isLoading = false
                    val movieList = (moviesState as ApiResult.Success).data.results
                    results = movieList
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(movieList) { items ->
                            MovieCard(title = items.title,
                                image = items.posterPath,
                                onClicked = {
                                    val path = "${NavigationItem.MovieDetail.route}/${items.id}"
                                    navController.navigate(route = path)
                                })
                        }
                    }
                }
                is ApiResult.Error -> {
                    isLoading = false
                    val exception = (moviesState as ApiResult.Error).exception
                    error = exception
                }
            }
        },
        isLoading = isLoading,
        hasError = error
    )
}

@Composable
fun MovieCard(title: String, image: String, onClicked: () -> Unit) {
    Column (modifier = Modifier
        .padding(8.dp)
        .clickable { onClicked() },
        horizontalAlignment = Alignment.Start){

        CharacterImg(imgURL = Constants.POSTER_IMAGE_BASE_URL+ image)
        TextCustom(text = title)
    }
}


@Composable
fun CharacterImg(imgURL: String) {
    Card(
        shape = RectangleShape,
        border = BorderStroke(width = 2.dp, color = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        AsyncImage(
            alignment = Alignment.Center,
            model = imgURL,
            contentDescription = "Translated description of what the image contains"
        )
    }
}