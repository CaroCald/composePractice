package com.example.practicecompose.features.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.practicecompose.R
import com.example.practicecompose.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.commons.components.text.TextCustom
import com.example.practicecompose.data.remote.ApiResult
import com.example.practicecompose.data.remote.models.movies.Result

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
        customBody = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextCustom(text = stringResource(R.string.title_welcome))
                Spacer(modifier = Modifier.height(8.dp))
            }

            when (moviesState) {
                is ApiResult.Loading -> {
                    isLoading = (moviesState as ApiResult.Loading).isLoading
                }
                is ApiResult.Success -> {
                    isLoading = false
                    val movieList = (moviesState as ApiResult.Success).data.results
                    results = movieList
                    LazyColumn {
                        items(movieList) { result ->
                            TextCustom(text = result.title)
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