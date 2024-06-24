package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.PlayersViewModel
import com.example.mtggamemaster.widgets.AddPlayerActionButton
import com.example.mtggamemaster.widgets.PlayerCard
import com.example.mtggamemaster.widgets.PlayerGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameSessionScreen() {
    val viewModel: PlayersViewModel = viewModel()
    val testingPlayerList = ArrayDeque<Player>(listOf(Player(name = "Manuel", life = 20), Player(name = "Alex", life = 20), Player(name = "Max", life = 20)))
    Scaffold(
            floatingActionButton = {
                    AddPlayerActionButton(onConfirmation = { name -> viewModel.addPlayer(player = Player(name = name)) })
            }
        ) { innerPadding ->
        PlayerGrid(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
    }
}