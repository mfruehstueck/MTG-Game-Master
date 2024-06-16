package com.example.mtggamemaster.models.card

class Card(
    //params
    var name: String?,
    var layout: String?,
    var cmc: Int?,
    var colors: List<String>?,
    var colorIdentity: List<String>?,
    var type: String?,
    var supertypes: List<String>?,
    var types: List<String>?,
    var subtypes: List<String>?,
    var rarity: String?,
    var set: String?,
    var setName: String?,
    var text: String?,
    var flavor: String?,
    var artist: String?,
    var number: String?,
    var power: String?,
    var toughness: String?,
    var loyality: String?,
    var multiverseid: String?,

    // DOCUMENTAION: other queryParams
    // see https://docs.magicthegathering.io/
    //  language        : The language the card is printed in. Use this parameter along with the name parameter when searching by foreignName
    //  gameFormat      : The game format, such as Commander, Standard, Legacy, etc. (when used, legality defaults to Legal unless supplied)
    //  legality        : The legality of the card for a given format, such as Legal, Banned or Restricted.
    //  page            : The page of data to request
    //  pageSize        : The amount of data to return in a single request. The default (and max) is 100.
    //  orderBy         : The field to order by in the response results
    //  random          : Fetch any number of cards (controlled by pageSize) randomly
    //  contains        : Filter cards based on whether or not they have a specific field available (like imageUrl)

    //response
    var names: List<String>?,
    var manaCost: String?,
    var variations: List<String>?,
    var imageUrl: String?,
    var watermark: String?,
    var releaseDate: String?,
    var rulings: Map<String, String>?,
    //var foreignNames: List<String>, used for other languages
    var printings: List<String>?,
    var originalText: String?,
    var originalType: String?,
    var legalities: Map<String, String>?,
    var id: String,

    var isFavorite: Boolean = false
) {
    constructor() : this(
        name = "",
        layout = "",
        cmc = 0,
        colors = emptyList(),
        colorIdentity = emptyList(),
        type = "",
        supertypes = emptyList(),
        types = emptyList(),
        subtypes = emptyList(),
        rarity = "",
        set = "",
        setName = "",
        text = "",
        flavor = "",
        artist = "",
        number = "",
        power = "",
        toughness = "",
        loyality = "",
        multiverseid = "",

        names = emptyList(),
        manaCost = "",
        variations = emptyList(),
        imageUrl = "",
        watermark = "",
        releaseDate = "",
        rulings = emptyMap(),
        printings = emptyList(),
        originalText = "",
        originalType = "",
        legalities = emptyMap(),
        id = ""
    )
}

//data class Legality(
//    var format: String,
//    var legality: ELegality
//)

//enum class ELegality {
//    Legal,
//    Restricted
//}