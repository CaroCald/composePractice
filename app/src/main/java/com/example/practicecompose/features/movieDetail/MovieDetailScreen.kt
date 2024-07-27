package com.example.practicecompose.features.movieDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.practicecompose.domain.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.domain.commons.components.toolbar.ToolBarCustom
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.constants.Constants
import com.example.practicecompose.data.remote.models.movies.MovieDetail
import com.example.practicecompose.features.movies.CharacterImg
import com.example.practicecompose.features.movies.MoviesViewModel

@Composable
fun MovieDetailScreen(navHostController: NavHostController,
                      id:String,
                      moviesVieModel: MoviesViewModel = hiltViewModel<MoviesViewModel>(),) {

    val movieState by moviesVieModel.movieDetailState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<Throwable?>(null) }
    var results by remember { mutableStateOf(MovieDetail()) }

    LaunchedEffect(Unit){
        moviesVieModel.getMovieDetail(id)
    }

    ScaffoldCustom(
        customToolBar = { ToolBarCustom(navController = navHostController,
            title = "Detalle",
            hasBackButton = true) },
        customBody = {
            when (movieState) {
                is ApiResult.Loading -> {
                    isLoading = (movieState as ApiResult.Loading).isLoading
                }
                is ApiResult.Success -> {
                    isLoading = false
                    val movieDetail = (movieState as ApiResult.Success).data
                    results = movieDetail

                    Column(
                       modifier = Modifier
                           .padding(20.dp)
                           .verticalScroll(rememberScrollState())
                           ,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        TextCustom(text = movieDetail.title)
                        Box (modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)){
                            CharacterImg(imgURL = Constants.POSTER_IMAGE_BASE_URL + movieDetail.posterPath)
                        }
                        TextCustom(text = movieDetail.overview, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(20.dp))
                        TextCustom(text = movieDetail.releaseDate)
                        Spacer(modifier = Modifier.height(10.dp))
                        TextCustom(text = movieDetail.status)
                    }

                }
                is ApiResult.Error -> {
                    isLoading = false
                    val exception = (movieState as ApiResult.Error).exception
                    error = exception
                }
            }
        },
        isLoading = isLoading,
        hasError = error
    )
}
