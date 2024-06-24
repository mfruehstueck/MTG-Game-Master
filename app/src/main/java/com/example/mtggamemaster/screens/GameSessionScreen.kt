package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.PlayersViewModel
import com.example.mtggamemaster.widgets.AddPlayerActionButton
import com.example.mtggamemaster.widgets.PlayerCard
import com.example.mtggamemaster.widgets.PlayerGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSessionScreen(navController: NavController) {
    val viewModel: PlayersViewModel = viewModel()

    BaseScreen(
        title = "Game Session",
        navController = navController,
        content = { innerPadding ->
            Scaffold(
                modifier = Modifier.padding(innerPadding),
                floatingActionButton = {
                    AddPlayerActionButton(onConfirmation = { name -> viewModel.addPlayer(player = Player(name = name)) })
                },
                content = { innerPadding -> PlayerGrid(viewModel = viewModel, modifier = Modifier.padding(innerPadding)) }
            )
        }
    )
}