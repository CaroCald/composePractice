package com.example.practicecompose.features.movies.moviesList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.practicecompose.domain.commons.components.bottomBar.BottomNavigationBar
import com.example.practicecompose.domain.commons.components.input.CustomSearchBar
import com.example.practicecompose.domain.commons.components.scaffold.ScaffoldCustom
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.domain.commons.utils.StateUtils
import com.example.practicecompose.data.remote.constants.Constants
import com.example.practicecompose.data.remote.models.movies.Result
import com.example.practicecompose.features.movies.MoviesViewModel
import com.example.practicecompose.navigation.NavigationItem

@Composable
fun MovieScreen(
    navController: NavHostController,
    moviesViewModel: MoviesViewModel = hiltViewModel()
) {
    val movieState by moviesViewModel.movieState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var filteredMovies by remember { mutableStateOf<List<Result>?>(null) }

    LaunchedEffect(Unit) {
        moviesViewModel.fetchMovies()
    }

    // Update API state
    LaunchedEffect(movieState) {
        val newApiState = StateUtils.processApiResult(
            result = movieState,
            onSuccess = { /* Success handled in UI */ },
            onError = { /* Error handled by ScaffoldCustom */ }
        )
        moviesViewModel.updateApiState(newApiState)
    }

    // Update filtered movies when search query changes
    LaunchedEffect(searchQuery, moviesViewModel.getMoviesList()) {
        val allMovies = moviesViewModel.getMoviesList()
        filteredMovies = if (searchQuery.isBlank()) {
            allMovies
        } else {
            moviesViewModel.searchMoviesByTitle(searchQuery)
        }
    }

    ScaffoldCustom(
        customBottomBar = { BottomNavigationBar(navController) },
        customBody = {
            Box {
                if (movieState is com.example.practicecompose.data.remote.ApiResult.Success) {
                    val movieList = filteredMovies ?: moviesViewModel.getMoviesList()
                    if (movieList != null) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            // Header
                            TextCustom(
                                text = "Movies",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // Search Bar
                            CustomSearchBar(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                placeholder = "Search movies...",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                            )

                            // Results count
                            if (searchQuery.isNotBlank()) {
                                TextCustom(
                                    text = "${movieList.size} result${if (movieList.size != 1) "s" else ""} found",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                contentPadding = PaddingValues(vertical = 8.dp),
                                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
                                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
                            ) {
                                items(movieList) { movie ->
                                    MovieCard(
                                        title = movie.title ?: "",
                                        image = movie.posterPath ?: "",
                                        onClicked = {
                                            val path = "${NavigationItem.MovieDetail.route}/${movie.id}"
                                            navController.navigate(route = path)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        onClickError = {
            moviesViewModel.restoreState()
        },
        isLoading = moviesViewModel.apiState.isLoading,
        hasError = moviesViewModel.apiState.error
    )
}

@Composable
fun MovieCard(title: String, image: String, onClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { onClicked() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            AsyncImage(
                model = Constants.POSTER_IMAGE_BASE_URL + image,
                contentDescription = "Movie poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                TextCustom(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}