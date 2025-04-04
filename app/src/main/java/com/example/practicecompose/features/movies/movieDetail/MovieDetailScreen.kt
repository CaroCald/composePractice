package com.example.practicecompose.features.movies.movieDetail

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.practicecompose.R
import com.example.practicecompose.domain.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.domain.commons.components.toolbar.ToolBarCustom
import com.example.practicecompose.data.remote.constants.Constants
import com.example.practicecompose.features.movies.moviesList.ImageCard
import com.example.practicecompose.features.movies.MoviesViewModel

@Composable
fun MovieDetailScreen(navHostController: NavHostController,
                      id:String,
                      moviesVieModel: MoviesViewModel = hiltViewModel<MoviesViewModel>(),) {

    LaunchedEffect(Unit){
        moviesVieModel.getMovieDetail(id)
    }

    ScaffoldCustom(
        customToolBar = { ToolBarCustom(navController = navHostController,
            title = stringResource(R.string.title_detail),
            hasBackButton = true) },
        customBody = {
            moviesVieModel.EventApiDetail(onSuccess = {
                val movieDetail = moviesVieModel.getDetailMovies()
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    TextCustom(text = movieDetail.title)
                    Box (modifier = Modifier.padding(horizontal = 40.dp, vertical = 10.dp)){
                        ImageCard(imgURL = Constants.POSTER_IMAGE_BASE_URL + movieDetail.posterPath)
                    }
                    TextCustom(text = movieDetail.overview, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(20.dp))
                    TextCustom(text = movieDetail.releaseDate)
                    Spacer(modifier = Modifier.height(10.dp))
                    TextCustom(text = movieDetail.status)
                }

            }, onError = {

            })
        },
        onClickError = {
            moviesVieModel.restoreState()
        },
        isLoading = moviesVieModel.apiState.isLoading,
        hasError = moviesVieModel.apiState.error
    )
}
