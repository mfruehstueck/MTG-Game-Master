package com.example.mtggamemaster.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.PlayersViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun PlayerGrid(
    viewModel: PlayersViewModel,
    modifier: Modifier
) {
    val players by viewModel.players.collectAsState()
    Column {
        LazyColumn {
            items(players) { player ->
                PlayerCard(
                    viewModel = viewModel,
                    player = player,
                    onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                    onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
                )
            }
        }
    }

}

@Composable
fun PlayerCard(
    viewModel: PlayersViewModel,
    player: Player,
    onPlusClick: (String) -> Unit = {},
    onMinusClick: (String) -> Unit = {}
) {
    OutlinedCard(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Text(
                text = player.name,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
            LifeSection(
                player = player,
                playerName = player.name,
                life = player.life,
                onPlusClick = onPlusClick,
                onMinusClick = onMinusClick)
        }

    }
}

@Composable
fun LifeSection(
    player: Player,
    playerName: String,
    life: Int,
    onPlusClick: (String) -> Unit = {},
    onMinusClick: (String) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Outlined.KeyboardArrowUp,
            contentDescription = "no",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
                .clickable {
                    onPlusClick(playerName)
                },
        )
        Text(
            text = player.life.toString(),
            fontSize = 70.sp,
            textAlign = TextAlign.Center,
        )
        Icon(
            Icons.Rounded.KeyboardArrowDown,
            contentDescription = "no",
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
                .clickable {
                    onMinusClick(playerName)
                }
        )
    }
}

@Composable
fun AddPlayerCard(
    onAddClick: (String) -> Unit = {},
) {
    OutlinedCard(
        modifier = Modifier
            .size(200.dp, 200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Text(
            text = "Add Player",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
        Icon(
            Icons.Outlined.Add,
            contentDescription = "no"
        )
    }
}