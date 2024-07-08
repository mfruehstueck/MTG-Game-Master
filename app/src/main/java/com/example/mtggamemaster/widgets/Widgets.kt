package com.example.mtggamemaster.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Alert(
    title: String,
    text: String,
    shouldShowDialog: MutableState<Boolean>
) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = { Text(text = title) },
            text = { Text(text = text) },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }
}

@Composable
fun SimpleFloatingActionButton(
    text: String = "Add",
    icon: @Composable () -> Unit = { Icon(Icons.Default.Add, contentDescription = "Add") },
    onClick: () -> Unit = {}
) {
    ExtendedFloatingActionButton(onClick = { onClick() }) {
        icon()
        if (text != "") Text(text = text)
    }
}

@Composable
fun simpleInputField(
    lableModifier: Modifier = Modifier
        .width(100.dp),
    inputModifier: Modifier = Modifier
        .fillMaxWidth(),
    lable: String = "",
    preview: String = "",
    readonly: Boolean = false
): String {
    var text by remember { mutableStateOf(preview) }

    Row(
        modifier = Modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (lable != "") {
            Text(
                modifier = lableModifier,
                text = lable
            )
        }
        OutlinedTextField(
            modifier = inputModifier,
            value = text,
            onValueChange = { text = it },

            readOnly = readonly
        )
    }

    return text
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDropDownMenu(
    modifier: Modifier = Modifier,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        modifier = modifier
            .padding(4.dp),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selected,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selected = option
                        expanded = false
                    })

            }
        }
    }
}