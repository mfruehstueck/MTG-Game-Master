package com.example.mtggamemaster.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.models.Deck

@Composable
fun DeckWidget(
    deck: Deck,
    navController: NavController,

    viewOnly: Boolean = false,

    onFavoriteClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},
    onClick: (String) -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(false) }
    isFavorite = deck.isFavorite

    Card(
        modifier = Modifier
            .clickable { onClick(deck.id) }
            .fillMaxWidth()
            .padding(5.dp),

        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp, 0.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Deck"
            )
            Text(
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 0.dp),
                text = deck.name
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            if (!viewOnly) {
                IconButton(
                    onClick = {
                        navController.navigate("${Screens.deckeditscreen}/${deck.id}")
                    }) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        imageVector = Icons.Default.Create,
                        contentDescription = "Edit"
                    )
                }
                IconButton(
                    onClick = {
                        onFavoriteClick(deck.id)
                        isFavorite = !isFavorite
                    }) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like!"
                    )
                }
                IconButton(
                    onClick = {
                        onDeleteClick(deck.id)
                    }) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Like!"
                    )
                }
            }
        }
    }
}

@Composable
fun DeckEditWidget(
    deck: Deck,
    navController: NavController
) {
    Column {
        simpleInputField(lable = "ID", preview = deck.id, readonly = true)
        deck.name = simpleInputField(lable = "Name", preview = deck.name)
        simpleInputField(lable = "Card count", preview = "${deck.cardList.size}", readonly = true)
        simpleInputField(lable = "Favorite", preview = "${deck.isFavorite}", readonly = true)
    }
}

@Composable
fun DeckWidgetList(
    deckList: List<Deck>,
    navController: NavController,

    viewOnly: Boolean = false,

    onFavoriteClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},

    innerPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        items(deckList) { deck ->
            DeckWidget(
                deck = deck,
                navController = navController,

                viewOnly = viewOnly,

                onFavoriteClick = { deckID ->
                    onFavoriteClick(deckID)
                },
                onDeleteClick = { deckID ->
                    onDeleteClick(deckID)
                }
            ) { deckID ->
                navController.navigate("${Screens.deckdetailscreen}/$deckID")
            }
        }
    }
}

@Composable
fun AddToDeckDialog(
    optionList: List<Deck>,
    onDismissRequest: () -> Unit,
    onConfirmation: (List<Deck>) -> Unit,
) {
    val selectedOptions = remember {
        mutableStateListOf<Deck>()
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
                text = "Choose deck to add card to",
                Modifier.padding(12.dp)
            )
            LazyColumn {
                items(optionList) { option ->
                    Row {
                        Checkbox(
                            checked = selectedOptions.contains(option),
                            onCheckedChange = { checked ->
                                if (checked) selectedOptions.add(option)
                                else selectedOptions.remove(option)
                            })
                        Text(text = option.name)
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
                    enabled = selectedOptions.isNotEmpty(),
                    onClick = {
                        onConfirmation(selectedOptions)
                        onDismissRequest()
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Confirm")
                }
            }
        }


//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
////                .height(250.dp)
//                .padding(16.dp),
//
//            ) {
//
//
//        }
    }
}