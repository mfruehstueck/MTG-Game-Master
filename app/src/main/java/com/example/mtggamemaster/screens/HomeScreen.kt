package com.example.mtggamemaster.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.player.PlayerViewModelFactory
import com.example.mtggamemaster.viewmodels.player.PlayersViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val repository = MTGRepository()
    val factory = PlayerViewModelFactory(repository = repository)
    val viewmodel: PlayersViewModel = viewModel(factory = factory)

    BaseScreen(
        title = "Home",
        navController = navController,
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Player Statistics"
                )
                val gamesessions = viewmodel.getAll()
                val players: MutableSet<Player> = mutableSetOf()

                gamesessions?.forEach { gamesession ->
                    players.addAll(gamesession.players)
                }

//                LazyColumn {
//                    item {
//                        PlayerStatRow(
//                            modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 4.dp),
//                            id = "ID",
//                            name = "Name",
//                            wins = "Wins",
//                            losses = "Losses"
//                        )
//                    }
//                    items(players.toList()) { player ->
//                        PlayerStatRow(
//                            modifier = Modifier.padding(0.dp, 2.dp),
//                            id = player.id,
//                            name = player.name,
//                            wins = player.wins.toString(),
//                            losses = player.losses.toString()
//                        )
//                    }
//                }
                PlayerStatistics(
                    listOf("ID", "Name", "Wins", "Losses"),
                    players.toList()
                )
            }
        }
    )
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun PlayerStatistics (
    headers: List<String>,
    players: List<Player>
) {
    // Each cell of a column must have the same weight.
    val columnWeights = listOf(
        .25f,
        .25f,
        .25f,
        .25f
    )

    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Here is the header
        item {
            Row(Modifier.background(Color.Gray)) {
                for (i in headers.indices) {
                    TableCell(text = headers[i], weight = columnWeights[i])
                }
            }
        }
        // Here are all the lines of your table.
        items(players) {player ->
            val playerInfo: List<String> = listOf(
                player.id,
                player.name,
                player.wins.toString(),
                player.losses.toString()
            )
            Row(Modifier.fillMaxWidth()) {
                for (i in headers.indices) {
                    TableCell(text = playerInfo[i], weight = columnWeights[i])
                }
            }
        }
    }
}