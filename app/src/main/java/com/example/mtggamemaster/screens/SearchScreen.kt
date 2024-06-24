package com.example.mtggamemaster.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtggamemaster.Screens
import com.example.mtggamemaster.data.card.CardRepository
import com.example.mtggamemaster.viewmodels.card.CardViewModel
import com.example.mtggamemaster.viewmodels.card.CardViewModelFactory
import com.example.mtggamemaster.widgets.SimpleFloatingActionButton
import com.example.mtggamemaster.widgets.simpleTextField

@Composable
fun SearchScreen(navController: NavController) {
    val repository = CardRepository()
    val factory = CardViewModelFactory(repository = repository)
    val viewModel: CardViewModel = viewModel(factory = factory)

    fun toggleFavorite(cardID: String) {
        viewModel.toggleFavorite(cardID)
    }

    BaseScreen(
        title = "Cards",
        navController = navController,
        floatingActionButton = {
            SimpleFloatingActionButton(
                text = "",
                icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                onClick = {
                    navController.navigate("${Screens.cardscreen}")
                }
            )
        },
        content = { innerPadding ->
            Column {
                simpleTextField(lable = "Card Name")
                simpleTextField(lable = "Card Types")
                simpleTextField(lable = "Card Text")
                CompareOperator("Set")
                CompareOperator("Mana cost converted")
                Row {
                    CompareOperator("P")
                    CompareOperator("T")
                }
                //TODO: checkboxes for Colors
                simpleTextField(lable = "Colors")

                //TODO: checkboxes for Rarity
                simpleTextField(lable = "Rarity")

                //TODO: checkboxes for Other
                simpleTextField(lable = "Other")
            }
        }
    )
}

@Composable
fun CompareOperator(
    lable: String
) {
    Row {
        Text(text = lable)
        SimpleDropDownMenu(items = listOf("=", ">", "<"))
        simpleTextField()
    }
}

@Composable
fun <T> SimpleDropDownMenu(
    items: List<T>
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("") }
    var size by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        items.forEach { lable ->
            DropdownMenuItem(
                text = { Text(lable.toString()) },
                onClick = {
                    selected = lable.toString()
                    expanded = false
                }
            )
        }
    }
}