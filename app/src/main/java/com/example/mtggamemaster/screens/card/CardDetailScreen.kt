package com.example.mtggamemaster.screens.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.screens.BaseScreen
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.widgets.CardWidgetDetail
import com.example.mtggamemaster.widgets.SimpleFloatingActionButton

@Composable
fun CardDetailScreen(cardID: String, navController: NavController) {
    val repository = CardRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewmodel: CardViewModel = viewModel(factory = factory)
    val currentCard = viewmodel.getCardByID(cardID) /*.collectAsState(initial = null).value*/

    if (currentCard == null) return Text(text = "Could not find card with id $cardID")

    BaseScreen(
        title = currentCard.name!!,
        navController = navController,
        floatingActionButton = {
            SimpleFloatingActionButton(
                text = "Add to deck",
                onClick = { navController.navigate("${Screens.deckscreen}") }
            )
        },
        content = { innerPadding ->
            CardWidgetDetail(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
                currentCard = currentCard,
                navController = navController,
                onFavoriteClick = { viewmodel.toggleFavorite(currentCard.id!!) }
            )
//                Column(
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
//
//                        .padding(innerPadding),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Column (
//                        horizontalAlignment = Alignment.CenterHorizontally,
////                        verticalArrangement = Arrangement.Center
//                    ){
//                        CardWidget(
//                            card = currentCard,
//                            navController = navController
//                        )
//                    }
//                    CardWidgetDetail(currentCard)
//                }

//                navController.navigate("${navController.previousBackStackEntry?.destination?.route}")


        }
    )
}