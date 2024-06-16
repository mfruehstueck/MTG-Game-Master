package com.example.mtggamemaster.widgets

import android.widget.Space
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.models.Deck

@Composable
fun DeckWidget(
    deck: Deck,
    navController: NavController,

    onDeckClick: (String) -> Unit = {},
    onFavoriteClick: (String) -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(false) }
    isFavorite = deck.isFavorite

    Card(
        modifier = Modifier
//            .clickable { onCardClick(card.id) }

            .fillMaxWidth(1f)
            .padding(5.dp),

        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
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
                    .fillMaxWidth(0.75f)
            )
            IconButton(
                onClick = {
                isFavorite = !isFavorite
            }) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like!"
                )
            }
        }
    }
}

@Composable
fun DeckWidgetList(
    deckList: List<Deck>,
    navController: NavController,
    innerPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        items(deckList) { deck ->
            DeckWidget(deck = deck, navController = navController) { deckID ->
                navController.navigate("${Screens.deckdetailscreen}/$deckID")
            }
        }
    }
}