package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.widgets.CardWidget
import com.example.mtggamemaster.widgets.CardWidgetDetail

@Composable
fun CardDetailScreen(cardID: String, navController: NavController) {
    val repository = CardRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewmodel: CardViewModel = viewModel(factory = factory)
    val currentCard = viewmodel.getCardByID(cardID).collectAsState(initial = null).value

    BaseScreen(
        title = "Card.kt Detail",
        navController = navController,
        content = { innerPadding ->
            if (currentCard != null) {
                CardWidgetDetail(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding),
                    currentCard = currentCard,
                    navController = navController
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
            } else {
                Text(text = "Could not find card with id $cardID")
//                navController.navigate("${navController.previousBackStackEntry?.destination?.route}")
            }

        }
    )
}