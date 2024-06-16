package com.example.mtggamemaster

import java.net.URL

class ENV {
    companion object {
        var CARD_ENDPOINT_VERSION: String = "v1"
        var API_URL: URL = URL("https://api.magicthegathering.io/$CARD_ENDPOINT_VERSION")
    }
}

enum class CardEndpoints {
    cards,
    sets,
    types,
    subtypes,
    supertypes,
    formats
}

enum class Screens {
    homescreen,
    deckscreen,
    deckdetailscreen,
    cardscreen,
    carddetailscreen,
    favoritescreen,
    gamesscreen,
    gamesessionscreen
}

enum class RoutArguments {
    deckID,
    cardID,
    gameID
}