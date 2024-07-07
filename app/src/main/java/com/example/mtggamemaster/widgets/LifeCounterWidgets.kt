package com.example.mtggamemaster.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.CallEnd
import androidx.compose.material.icons.outlined.Dangerous
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.models.GameSession
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.viewmodels.player.PlayersViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewPlayerGrid(
    gamesessionID: String,
    viewModel: PlayersViewModel,
    modifier: Modifier,
    navController: NavController
) {
    val players by viewModel.players.collectAsState()
    var contextMenuPlayer by rememberSaveable {
        mutableStateOf<Player?>(null)
    }
    if (contextMenuPlayer != null) {
        PlayerActionSheet(
            player = contextMenuPlayer!!,
            onDismissSheet = { contextMenuPlayer = null },
            onDeleteClick = { player -> viewModel.removePlayer(gamesessionID, player) }
        )
    }
    Column() {
        val n = players.size
        var c = 0
        if (n % 2 != 0) {
            PlayerCard(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = {
                            contextMenuPlayer = players[0]
                        }
                    ),
                viewModel = viewModel,
                gamesessionID = gamesessionID,
                player = players[0],
                onPlusClick = { playerID ->
                    viewModel.gainLife(
                        gamesessionID,
                        playerID = playerID
                    )
                },
                onMinusClick = { playerID ->
                    viewModel.loseLife(
                        gamesessionID,
                        playerID = playerID
                    )
                }
            )
            c++
        }
        if (n == 2) {
            PlayerCard(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = {
                            contextMenuPlayer = players[0]
                        }
                    ),
                viewModel = viewModel,
                gamesessionID = gamesessionID,
                player = players[0],
                onPlusClick = { playerID ->
                    viewModel.gainLife(
                        gamesessionID,
                        playerID = playerID
                    )
                },
                onMinusClick = { playerID ->
                    viewModel.loseLife(
                        gamesessionID,
                        playerID = playerID
                    )
                },
            )
            c++
            PlayerCard(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = {
                            contextMenuPlayer = players[1]
                        }
                    ),
                viewModel = viewModel,
                gamesessionID = gamesessionID,
                player = players[1],
                onPlusClick = { playerID ->
                    viewModel.gainLife(
                        gamesessionID,
                        playerID = playerID
                    )
                },
                onMinusClick = { playerID ->
                    viewModel.loseLife(
                        gamesessionID,
                        playerID = playerID
                    )
                }
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
                        .weight(1f)
                        .combinedClickable(
                            onClick = {},
                            onLongClick = {
                                contextMenuPlayer = players[i]
                            }
                        ),
                    viewModel = viewModel,
                    gamesessionID = gamesessionID,
                    player = players[i],
                    onPlusClick = { playerID ->
                        viewModel.gainLife(
                            gamesessionID,
                            playerID = playerID
                        )
                    },
                    onMinusClick = { playerID ->
                        viewModel.loseLife(
                            gamesessionID,
                            playerID = playerID
                        )
                    }
                )
                if (i + 1 <= n) {
                    PlayerCard(
                        modifier = Modifier
                            .padding(3.dp)
                            .weight(1f)
                            .combinedClickable(
                                onClick = {},
                                onLongClick = {
                                    contextMenuPlayer = players[i + 1]
                                }
                            ),
                        viewModel = viewModel,
                        gamesessionID = gamesessionID,
                        player = players[i + 1],
                        onPlusClick = { playerID ->
                            viewModel.gainLife(
                                gamesessionID,
                                playerID = playerID
                            )
                        },
                        onMinusClick = { playerID ->
                            viewModel.loseLife(
                                gamesessionID,
                                playerID = playerID
                            )
                        }
                    )
                }

            }

        }
        MiddleBar(gamesessionID, viewModel = viewModel, navController = navController)
    }
}

@Composable
fun WinPlayerDialog(
    optionList: List<Player>,
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
) {
    var selected by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .height(420.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Choose winning player",
                Modifier.padding(12.dp)
            )
            LazyColumn {
                items(optionList) { option ->
                    Row {
                        RadioButton(
                            selected = selected,
                            onClick = {
                                selected = !selected
                                selectedOption = option.id
                            }
                        )
                        Text(text = option.name)
                    }
                }
                item {
                    Row {
                        RadioButton(
                            selected = selected,
                            onClick = {
                                selected = !selected
                                selectedOption = "draw"
                            }
                        )
                        Text(text = "draw")
                    }
                }
            }
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
                    enabled = selectedOption.isNotEmpty(),
                    onClick = {
                        onConfirmation(selectedOption)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerActionSheet(
    player: Player,
    onDismissSheet: () -> Unit,
    onDeleteClick: (Player) -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissSheet) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    onDeleteClick(player)
                    onDismissSheet()
                }
        ) {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = "Delete Player",
            )
            Text(text = "Delete ${player.name}?")
        }
    }
}

@Composable
fun PlayerCard(
    modifier: Modifier,
    gamesessionID: String,
    viewModel: PlayersViewModel,
    player: Player,
    onPlusClick: (String) -> Unit = {},
    onMinusClick: (String) -> Unit = {},
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
            when (selectedSymbol) {
                1 -> {
                    LifeSection(
                        player = player,
                        playerID = player.id,
                        life = life,
                        onPlusClick = onPlusClick,
                        onMinusClick = onMinusClick
                    )
                }

                2 -> {
                    EnergySection(
                        player = player,
                        playerID = player.id,
                        energy = energy,
                        onEnergyPlusClick = { playerID ->
                            viewModel.gainEnergy(
                                gamesessionID,
                                playerID = playerID
                            )
                        },
                        onEnergyMinusClick = { playerID ->
                            viewModel.loseEnergy(
                                gamesessionID,
                                playerID = playerID
                            )
                        }
                    )
                }

                3 -> {
                    PoisonSection(
                        player = player,
                        playerID = player.id,
                        energy = energy,
                        onPoisonPlusClick = { playerName ->
                            viewModel.gainPoison(
                                gamesessionID,
                                playerID = playerName
                            )
                        },
                        onPoisonMinusClick = { playerName ->
                            viewModel.losePoison(
                                gamesessionID,
                                playerID = playerName
                            )
                        }
                    )
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
                contentDescription = "Energy",
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
                Icons.Outlined.Dangerous,
                contentDescription = "Poison",
            )
        }
    }
}


@Composable
fun LifeSection(
    player: Player,
    playerID: String,
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
                        onPlusClick(playerID)
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
                        onMinusClick(playerID)
                    }
            )
        }
    }

}

@Composable
fun EnergySection(
    player: Player,
    playerID: String,
    energy: Int,
    onEnergyPlusClick: (String) -> Unit = {},
    onEnergyMinusClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Energy",
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
                        onEnergyPlusClick(playerID)
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
                        onEnergyMinusClick(playerID)
                    }
            )
        }
    }

}

@Composable
fun PoisonSection(
    player: Player,
    playerID: String,
    energy: Int,
    onPoisonPlusClick: (String) -> Unit = {},
    onPoisonMinusClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Poison",
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
                        onPoisonPlusClick(playerID)
                    },
            )
            Text(
                text = player.poison.toString(),
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
                        onPoisonMinusClick(playerID)
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
    if (dialogOpen) {
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
    if (dialogOpen) {
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
    if (text != "") {
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
                text = "Enter the player's name:",
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
                    label = { Text("New Player") },
                    singleLine = true
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
    gamesessionID: String,
    viewModel: PlayersViewModel,
    navController: NavController
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
            AddPlayerIcon { name ->
                viewModel.addPlayer(
                    gamesessionID,
                    playerName = name
                )
            }
            DieIcon(viewModel = viewModel)
            StartingLifeIcon(viewModel = viewModel)
            EndGame(currentGameSession = viewModel.getByID(gamesessionID)!!) {
                viewModel.add()
                navController.navigate("${Screens.gamesessionscreen}/${gamesessionID}")
            }
        }
    }
}

@Composable
fun EndGame(
    currentGameSession: GameSession,
    onEndGame: () -> Unit = {}
) {
    var dialogOpen by remember { mutableStateOf(false) }
    Icon(
        Icons.Outlined.CallEnd,
        contentDescription = "no",
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp)
            .clickable {
                dialogOpen = true
            },
    )
    if (dialogOpen) {
        WinPlayerDialog(
            optionList = currentGameSession.players.toList(),
            onDismissRequest = { dialogOpen = false },
            onConfirmation = { selectedPlayer ->
                if (selectedPlayer != "draw") {
                    val currentPlayer =
                        currentGameSession.players.find { check -> check.id == selectedPlayer }!!
                    currentPlayer.wins++

                    currentGameSession.winner = currentPlayer
                }
                onEndGame()
            }
        )
    }
}

@Composable
fun StartingLifeIcon(
    viewModel: PlayersViewModel
) {
    var dialogOpen by remember { mutableStateOf(false) }
    Icon(
        Icons.Outlined.Favorite,
        contentDescription = "no",
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp)
            .clickable { dialogOpen = true }
    )
    if (dialogOpen) {
        StartingLifeDialog(
            onDismissRequest = { dialogOpen = false }
        )
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
fun StartingLifeDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit = {}
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Select starting life",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center
                )
                var text by remember {
                    mutableStateOf("")
                }
                TextField(
                    modifier = Modifier.padding(12.dp),
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Starting Life") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )

            }
        }
    }
}
