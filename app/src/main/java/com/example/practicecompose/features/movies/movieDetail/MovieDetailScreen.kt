package com.example.practicecompose.features.movies.movieDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.practicecompose.R
import com.example.practicecompose.domain.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.domain.commons.components.toolbar.ToolBarCustom
import com.example.practicecompose.domain.commons.utils.StateUtils
import com.example.practicecompose.data.remote.constants.Constants
import com.example.practicecompose.features.movies.MoviesViewModel

@Composable
fun MovieDetailScreen(
    navHostController: NavHostController,
    id: String,
    moviesVieModel: MoviesViewModel = hiltViewModel<MoviesViewModel>()
) {
    val movieDetailState by moviesVieModel.movieDetailState.collectAsState()

    LaunchedEffect(Unit) {
        moviesVieModel.getMovieDetail(id)
    }

    // Update API state
    LaunchedEffect(movieDetailState) {
        val newApiState = StateUtils.processApiResult(
            result = movieDetailState,
            onSuccess = { /* Success handled in UI */ },
            onError = { /* Error handled by ScaffoldCustom */ }
        )
        moviesVieModel.updateApiState(newApiState)
    }

    ScaffoldCustom(
        customToolBar = { 
            ToolBarCustom(
                navController = navHostController,
                title = stringResource(R.string.title_detail),
                hasBackButton = true
            ) 
        },
        customBody = {
            if (movieDetailState is com.example.practicecompose.data.remote.ApiResult.Success) {
                val movieDetail = moviesVieModel.getMovieDetail()
                if (movieDetail != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Poster Image
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            AsyncImage(
                                model = Constants.POSTER_IMAGE_BASE_URL + movieDetail.posterPath,
                                contentDescription = "Movie poster",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Title
                        TextCustom(
                            text = movieDetail.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Details (Release Date and Status)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            DetailItem(
                                label = "Release Date",
                                value = movieDetail.releaseDate
                            )

                            DetailItem(
                                label = "Status",
                                value = movieDetail.status
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        // Overview
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextCustom(
                                text = "Overview",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            TextCustom(
                                text = movieDetail.overview,
                                textAlign = TextAlign.Justify,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        },
        onClickError = {
            moviesVieModel.restoreState()
        },
        isLoading = moviesVieModel.apiState.isLoading,
        hasError = moviesVieModel.apiState.error
    )
}

@Composable
private fun DetailItem(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        TextCustom(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextCustom(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
