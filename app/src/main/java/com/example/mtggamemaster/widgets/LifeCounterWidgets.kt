package com.example.mtggamemaster.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AreaChart
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel

@Composable
fun NewPlayerGrid(
    viewModel: PlayersViewModel,
    modifier: Modifier
) {
    val players by viewModel.players.collectAsState()
    Column {
        Column {
            var n = players.size
            var c = 0
            if(n % 2 != 0) {
                PlayerCard(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    viewModel = viewModel,
                    player = players[0],
                    onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                    onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
                )
                c++
            }
            if (n == 2) {
                PlayerCard(
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f),
                    viewModel = viewModel,
                    player = players[0],
                    onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                    onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
                )
                c++
                PlayerCard(
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f),
                    viewModel = viewModel,
                    player = players[1],
                    onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                    onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
                )
                c++

            }
            for (i in c..<n step 2) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    PlayerCard(
                        modifier = Modifier
                            .padding(3.dp)
                            .weight(1f),
                        viewModel = viewModel,
                        player = players[i],
                        onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                        onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
                    )
                    if(i + 1 <= n) {
                        PlayerCard(
                            modifier = Modifier
                                .padding(3.dp)
                                .weight(1f),
                            viewModel = viewModel,
                            player = players[i + 1],
                            onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                            onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
                        )
                    }

                }

            }
            MiddleBar(viewModel = viewModel)
        }

    }
}
@Composable
fun PlayerGrid(
    viewModel: PlayersViewModel,
    modifier: Modifier
) {
    var contextMenuPlayer by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    val players by viewModel.players.collectAsState()
    when(players.size) {
        0 -> MiddleBar(viewModel = viewModel)
        1 -> Grid1Player(viewModel = viewModel, players = players)
        2 -> Grid2Players(viewModel = viewModel, players = players)
        3 -> Grid3Players(viewModel = viewModel, players = players)
        4 -> Grid4Players(viewModel = viewModel, players = players)
    }
    /*
        Column {
            LazyColumn {
                items(players) { player ->
                    PlayerCard(
                        modifier = modifier,
                        viewModel = viewModel,
                        player = player,
                        onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                        onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
                    )
                }
            }
        }
    */


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid1Player(
    viewModel: PlayersViewModel,
    players: List<Player>
) {
    Column {
        PlayerCard(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .padding(8.dp)
                .combinedClickable(
                    onClick = {},
                    onLongClick = { viewModel.removePlayer(players[0]) }
                ),
            viewModel = viewModel,
            player = players[0],
            onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName) },
            onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName) }
        )
        MiddleBar(viewModel = viewModel)
    }
}
@Composable
fun Grid2Players(
    viewModel: PlayersViewModel,
    players: List<Player>
) {
    Column(
    ) {
        PlayerCard(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(8.dp),
            viewModel = viewModel,
            player = players[0],
            onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
            onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
        )
        MiddleBar(viewModel = viewModel)
        PlayerCard(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            viewModel = viewModel,
            player = players[1],
            onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
            onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
        )
    }
}

@Composable
fun Grid3Players(
    viewModel: PlayersViewModel,
    players: List<Player>
) {
    Column {
        PlayerCard(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(8.dp),
            viewModel = viewModel, player = players[0],
            onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
            onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
        )
        Row {
            PlayerCard(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(8.dp, 0.dp, 4.dp, 8.dp),
                viewModel = viewModel,
                player = players[1],
                onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
            )
            PlayerCard(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 0.dp, 8.dp, 8.dp),
                viewModel = viewModel,
                player = players[2],
                onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
            )
        }
    }
}

@Composable
fun Grid4Players(
    viewModel: PlayersViewModel,
    players: List<Player>
) {
    Column {
        Row {
            PlayerCard(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f)
                    .padding(8.dp, 8.dp, 4.dp, 8.dp),
                viewModel = viewModel, player = players[0],
                onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
            )
            PlayerCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(4.dp, 8.dp, 8.dp, 8.dp),
                viewModel = viewModel, player = players[1],
                onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
            )
        }

        Row {
            PlayerCard(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(8.dp, 0.dp, 4.dp, 8.dp),
                viewModel = viewModel,
                player = players[2],
                onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
            )
            PlayerCard(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 0.dp, 8.dp, 8.dp),
                viewModel = viewModel,
                player = players[3],
                onPlusClick = { playerName -> viewModel.gainLife(playerName = playerName)},
                onMinusClick = { playerName -> viewModel.loseLife(playerName = playerName)}
            )
        }
    }
}

@Composable
fun PlayerCard(
    modifier: Modifier,
    viewModel: PlayersViewModel,
    player: Player,
    onPlusClick: (String) -> Unit = {},
    onMinusClick: (String) -> Unit = {}
) {
    var selectedSymbol by remember {
        mutableStateOf(1)
    }
    var life by remember {
        mutableStateOf(player.life)
    }
    var energy by remember {
        mutableStateOf(player.energy)
    }
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        modifier = modifier,
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
            when(selectedSymbol) {
                1 -> {
                    LifeSection(
                        player = player,
                        playerName = player.name,
                        life = life,
                        onPlusClick = onPlusClick,
                        onMinusClick = onMinusClick
                    )
                }
                2 -> {
                    EnergySection(
                        player = player,
                        playerName = player.name,
                        energy = energy,
                        onEnergyPlusClick = { playerName -> viewModel.gainEnergy(playerName = playerName) },
                        onEnergyMinusClick = { playerName -> viewModel.loseEnergy(playerName = playerName) }
                    )
                }
                3 -> {

                }
            }

            SymbolSelection(
                selectedSymbol = selectedSymbol,
                onSymbolSelected = { selectedSymbol = it }
            )
        }

    }
}

@Composable
fun SymbolSelection(
    selectedSymbol: Int,
    onSymbolSelected: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(12.dp)
                .background(
                    color = if (selectedSymbol == 1) Color.LightGray else Color.Transparent,
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(RoundedCornerShape(100.dp))
                .clickable { onSymbolSelected(1) }
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.Favorite,
                contentDescription = "Life",
            )
        }
        Box(
            modifier = Modifier
                .padding(12.dp)
                .background(
                    color = if (selectedSymbol == 2) Color.LightGray else Color.Transparent,
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(RoundedCornerShape(100.dp))
                .clickable { onSymbolSelected(2) }
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.Bolt,
                contentDescription = "Life",
            )
        }
        Box(
            modifier = Modifier
                .padding(12.dp)
                .background(
                    color = if (selectedSymbol == 3) Color.LightGray else Color.Transparent,
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(RoundedCornerShape(100.dp))
                .clickable(
                    onClick = { onSymbolSelected(3) },
                )
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.Accessibility,
                contentDescription = "Life",
            )
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Life",
            textAlign = TextAlign.Center,
            )
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
    
}

@Composable
fun EnergySection(
    player: Player,
    playerName: String,
    energy: Int,
    onEnergyPlusClick: (String) -> Unit = {},
    onEnergyMinusClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Energy",
            textAlign = TextAlign.Center,)
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
                        onEnergyPlusClick(playerName)
                    },
            )
            Text(
                text = player.energy.toString(),
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
                        onEnergyMinusClick(playerName)
                    }
            )
        }
    }
    
}

@Composable
fun AddPlayerActionButton(
    onConfirmation: (String) -> Unit = {}
) {
    var dialogOpen by remember { mutableStateOf(false) }
    ExtendedFloatingActionButton(onClick = { dialogOpen = true }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
        Text(text = "Add Player")
    }
    if(dialogOpen) {
        AddPlayerDialog(
            onDismissRequest = { dialogOpen = false },
            onConfirmation = onConfirmation
        )
    }
}
@Composable
fun AddPlayerIcon(
    onConfirmation: (String) -> Unit = {}
) {
    var dialogOpen by remember { mutableStateOf(false) }
    Icon(
        Icons.Outlined.Add,
        contentDescription = "no",
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp)
            .clickable { dialogOpen = true }
    )
    if(dialogOpen) {
        AddPlayerDialog(
            onDismissRequest = { dialogOpen = false },
            onConfirmation = onConfirmation
        )
    }
}

@Composable
fun AddPlayerDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    var isEnabled by remember { mutableStateOf(false) }
    if(text != "") {
        isEnabled = true
    } else {
        isEnabled = false
    }

    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),

        ) {
            Text(
                text = "Enter the player's name or choose an existing player:",
                Modifier.padding(12.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    modifier = Modifier.padding(12.dp),
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("New Player") }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        enabled = isEnabled,
                        onClick = {
                            onConfirmation(text)
                            onDismissRequest()
                                  },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}

@Composable
fun MiddleBar(
    viewModel: PlayersViewModel
) {
    OutlinedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row {
            AddPlayerIcon { name -> viewModel.addPlayer(player = Player(name = name)) }
            DieIcon(viewModel = viewModel)
        }
    }
}

@Composable
fun DieIcon(
    viewModel: PlayersViewModel
) {
    var dialogOpen by remember { mutableStateOf(false) }
    Icon(
        Icons.Outlined.Dataset,
        contentDescription = "no",
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp)
            .clickable { dialogOpen = true }
    )
    if (dialogOpen) {
        DieRollDialog(
            onDismissRequest = { dialogOpen = false },
            rolledNumber = viewModel.rollDie()
        )
    }
}
@Composable
fun DieRollDialog(
    onDismissRequest: () -> Unit,
    rolledNumber: Int
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "You rolled a $rolledNumber!",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun RemovePlayerContextMenu(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit
) {

}
