package com.example.mtggamemaster.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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