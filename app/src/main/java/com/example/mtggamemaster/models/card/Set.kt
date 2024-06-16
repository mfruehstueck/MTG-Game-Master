package com.example.mtggamemaster.models.card

import java.util.Date

data class Set(
    var code: String,
    var name: String,
    var type: String,
    var booster: List<String>,
    var releaseDate: Date,
    var block: String,
    var onlineOnly: Boolean
)
