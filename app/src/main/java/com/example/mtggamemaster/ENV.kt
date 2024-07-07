package com.example.mtggamemaster

import java.net.URL

class ENV {
    companion object {
        var CARD_ENDPOINT_VERSION: String = "v1"
        var API_URL: URL = URL("https://api.magicthegathering.io/$CARD_ENDPOINT_VERSION")
        var CARD_ENDPOINTS = mapOf (
            Pair(CardEndpoints.cards, "$API_URL/cards"),
            Pair(CardEndpoints.sets, "$API_URL/sets"),
            Pair(CardEndpoints.types, "$API_URL/types"),
            Pair(CardEndpoints.subtypes, "$API_URL/subtypes"),
            Pair(CardEndpoints.supertypes, "$API_URL/supertypes"),
            Pair(CardEndpoints.formats, "$API_URL/format"),
        )
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

enum class CardLayout {
    normal,
    split,
    flip,
    doublefaced,
    token,
    plane,
    scheme,
    phenomenon,
    leveler,
    vanguard,
    aftermath
}

enum class Screens {
    homescreen,
    deckscreen,
    deckeditscreen,
    deckdetailscreen,
    cardscreen,
    carddetailscreen,
    searchscreen,
    favoritescreen,
    gamesscreen,
    gamesessionscreen
}

enum class RoutArguments {
    deckID,
    cardID,
    gamesessionID
}

enum class APIError(code: Int) {
    Success(200),
    BadRequest(400),            //N: We could not process that action
    Forbidden(403),             //N: You exceeded the rate limit
    NotFound(404),              //N: The requested resource could not be found
    InternalServerError(500),   //N: We had a problem with our server. Please try again later
    ServiceUnvailable(503)      //N: We are temporarily offline for maintenance. Please try again later
}