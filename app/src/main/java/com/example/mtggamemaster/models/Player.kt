package com.example.mtggamemaster.models

data class Player(
    var name: String,
    var life: Int,
    var poison: Int = 0,
    var energy: Int = 0
)