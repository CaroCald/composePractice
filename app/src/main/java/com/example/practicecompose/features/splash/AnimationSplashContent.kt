package com.example.practicecompose.features.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.practicecompose.features.auth.AuthViewModel
import com.example.practicecompose.navigation.NavigationItem
import kotlinx.coroutines.delay

@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    navController: NavController,
    durationMillisAnimation: Int,
    delayScreen: Long,
    authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
) {

    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 0.5F,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )

        delay(timeMillis = delayScreen)

        var path = NavigationItem.Welcome.route
        if(authViewModel.isLogged()){
            path = NavigationItem.MovieList.route
        }
        navController.navigate(route = path)

    }
}