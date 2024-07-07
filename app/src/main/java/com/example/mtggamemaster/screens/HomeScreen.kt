package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens

@Composable
fun HomeScreen(navController: NavController) {
    BaseScreen(
        title = "Home",
        navController = navController,
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "${Screens.homescreen} not implemented yet")
            }
        }
    )
}