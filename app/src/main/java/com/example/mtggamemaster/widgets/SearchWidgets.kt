package com.example.mtggamemaster.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mtggamemaster.viewmodels.card.CardViewModel

@Composable
fun SearchWidget(
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        val lableWidth = 150.dp


        CardViewModel.currentFilter.name(
            simpleInputField(
                lableModifier = Modifier
                    .width(lableWidth),
                lable = "Card Name"
            )
        )
        CardViewModel.currentFilter.type(
            simpleInputField(
                lableModifier = Modifier
                    .width(lableWidth),
                lable = "Card Types"
            )
        )
        CardViewModel.currentFilter.text(
            simpleInputField(
                lableModifier = Modifier
                    .width(lableWidth),
                lable = "Card Text"
            )
        )
        CardViewModel.currentFilter.set(
            simpleInputField(
                lableModifier = Modifier
                    .width(lableWidth),
                lable = "Set"
            )
        )
        //TODO: change to selection list
//        CompareOperator(
//            lable = "Set",
//            lableModifier = Modifier
//                .width(lableWidth)
//        )

        //TODO: add these filters
//        CompareOperator(
//            lable = "Mana cost converted",
//            lableModifier = Modifier
//                .width(lableWidth)
//                .padding(4.dp)
//        )
//        CompareOperator(
//            lable = "P",
//            lableModifier = Modifier
//                .width(lableWidth)
//                .padding(4.dp)
//        )
//        CompareOperator(
//            lable = "T",
//            lableModifier = Modifier
//                .width(lableWidth)
//                .padding(4.dp)
//        )

        CheckboxGrid(
            lable = "Colors",
            items = listOf(
                "White",
                "Blue",
                "Black",
                "Red",
                "Green",
                "Colorless"
            ),
            onCheckedChange = { lable, checked ->
                CardViewModel.currentFilter.colors(lable, checked)
            }
        )
//        //TODO: checkboxes for Colors
//        simpleInputField(
//            lableModifier = Modifier
//                .width(lableWidth),
//            lable = "Colors"
//        )

        CheckboxGrid(
            lable = "Rarity",
            items = listOf(
                "Common",
                "Uncommon",
                "Rare",
                "Mythic"
            ),
            onCheckedChange = { lable, checked ->
                CardViewModel.currentFilter.rarity(lable, checked)
            }
        )
//        //TODO: checkboxes for Rarity
//        simpleInputField(
//            lableModifier = Modifier
//                .width(lableWidth), lable = "Rarity"
//        )

//        Other()
//        //TODO: checkboxes for Other
//        simpleInputField(
//            lableModifier = Modifier
//                .width(lableWidth), lable = "Other"
//        )
    }
}


@Composable
fun CheckboxGrid(
    lable: String = "",
    items: List<String>,
    onCheckedChange: (String, Boolean) -> Unit = { _: String, _: Boolean -> }
) {
    var idx = 0

    simpleInputField(
        inputModifier = Modifier
            .width(0.dp),
        lable = lable
    )

    while (idx < items.size) {
        Row {
            CheckboxWithLable(
                modifier = Modifier
                    .padding(16.dp, 0.dp),
                lable = items[idx++],
                onCheckedChange = { lable, checked -> onCheckedChange(lable, checked) }
            )
            CheckboxWithLable(
                modifier = Modifier
                    .padding(42.dp, 0.dp),
                lable = items[idx++],
                onCheckedChange = { lable, checked -> onCheckedChange(lable, checked) }
            )
        }
    }
}

@Composable
fun CheckboxWithLable(
    modifier: Modifier = Modifier,
    lable: String,
    onCheckedChange: (String, Boolean) -> Unit = { _: String, _: Boolean -> }
) {
    var checked by remember { mutableStateOf(true) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = !checked
                onCheckedChange(lable, checked)
            }
        )
        Text(
            modifier = Modifier
                .width(80.dp),
            text = lable
        )
    }
}

@Composable
fun CompareOperator(
    lableModifier: Modifier = Modifier,
    lable: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = lableModifier, text = lable)
        SimpleDropDownMenu(
            modifier = Modifier
                .width(84.dp),
            options = listOf("=", ">", "<", ">=", "<=")
        )
        simpleInputField(
            lableModifier = Modifier
                .width(0.dp),
            inputModifier = Modifier
                .fillMaxWidth()
        )
    }


}