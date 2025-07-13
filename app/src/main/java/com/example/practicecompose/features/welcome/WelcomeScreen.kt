package com.example.practicecompose.features.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practicecompose.R
import com.example.practicecompose.domain.commons.components.text.TextCustom
import com.example.practicecompose.navigation.NavigationItem

@Composable
fun WelcomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgroud_image),
            contentDescription = null,
            contentScale = ContentScale.Crop, // fill the entire screen nicely
            modifier = Modifier.fillMaxSize()
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).clickable {
                navController.navigate(route = NavigationItem.Login.route)
                }, // optional padding for text
        ) {

            TextCustom(
                text = stringResource(R.string.title_welcome),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = R.drawable.arrow_continue),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

