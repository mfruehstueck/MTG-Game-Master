package com.example.mtggamemaster.models.card

import org.json.JSONException
import org.json.JSONObject

data class Card(
    //params
    var name: String? = null,
    var layout: String? = null,
    var cmc: Int? = null,
    var colors: List<String>? = null,
    var colorIdentity: List<String>? = null,
    var type: String? = null,
    var supertypes: List<String>? = null,
    var types: List<String>? = null,
    var subtypes: List<String>? = null,
    var rarity: String? = null,
    var set: String? = null,
    var setName: String? = null,
    var text: String? = null,
    var flavor: String? = null,
    var artist: String? = null,
    var number: String? = null,
    var power: String? = null,
    var toughness: String? = null,
    var loyality: String? = null,
    var multiverseid: String? = null,

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
    var names: List<String>? = null,
    var manaCost: String? = null,
    var variations: List<String>? = null,
    var imageUrl: String? = null,
    var watermark: String? = null,
    var releaseDate: String? = null,
    var rulings: Map<String, String>? = null,
    //var foreignNames: List<String>, used for other languages
    var printings: List<String>? = null,
    var originalText: String? = null,
    var originalType: String? = null,
    var legalities: Map<String, String>? = null,
    var id: String? = null,

    var isFavorite: Boolean = false
) {
    fun update(card: Card) {
        this.name = card.name
        this.layout = card.layout
        this.cmc = card.cmc
        this.colors = card.colors
        this.colorIdentity = card.colorIdentity
        this.type = card.type
        this.supertypes = card.supertypes
        this.types = card.types
        this.subtypes = card.subtypes
        this.rarity = card.rarity
        this.set = card.set
        this.setName = card.setName
        this.text = card.text
        this.flavor = card.flavor
        this.artist = card.artist
        this.number = card.number
        this.power = card.power
        this.toughness = card.toughness
        this.loyality = card.loyality
        this.multiverseid = card.multiverseid

        this.names = card.names
        this.manaCost = card.manaCost
        this.variations = card.variations
        this.imageUrl = card.imageUrl
        this.watermark = card.watermark
        this.releaseDate = card.releaseDate
        this.rulings = card.rulings
        this.printings = card.printings
        this.originalText = card.originalText
        this.originalType = card.originalType
        this.legalities = card.legalities
        this.id = card.id
    }

    companion object {
        var cnt: Int = 0
    }

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
        rulings = mapOf(),
        printings = emptyList(),
        originalText = "",
        originalType = "",
        legalities = mapOf(),
        id = ""
    ) {
        cnt++
    }

    constructor(json: JSONObject) : this(
        name = json.getString("name"),
        layout = json.getString("layout"),
        cmc = json.getInt("cmc"),
        type = json.getString("type"),
        types = json.getJSONArray("types").toString().split(","),
        rarity = json.getString("rarity"),
        set = json.getString("set"),
        setName = json.getString("setName"),
        text = json.getString("text"),
        artist = json.getString("artist"),
        number = json.getString("number"),
        printings = json.getJSONArray("printings").toString().split(","),
        id = json.getString("id")
    ) {
        this.colors = try {
            json.getJSONArray("colors").toString().split(",")
        } catch (e: JSONException) {
            null
        }
        this.colorIdentity = try {
            json.getJSONArray("colorIdentity").toString().split(",")
        } catch (e: JSONException) {
            null
        }
        this.supertypes = try {
            json.getJSONArray("supertypes").toString().split(",")
        } catch (e: JSONException) {
            null
        }
        this.subtypes = try {
            json.getJSONArray("subtypes").toString().split(",")
        } catch (e: JSONException) {
            null
        }
        this.flavor = try {
            json.getString("flavor")
        } catch (e: JSONException) {
            null
        }
        this.power = try {
            json.getString("power")
        } catch (e: JSONException) {
            null
        }
        this.toughness = try {
            json.getString("toughness")
        } catch (e: JSONException) {
            null
        }
        this.loyality = try {
            json.getString("loyality")
        } catch (e: JSONException) {
            null
        }
        this.multiverseid = try {
            json.getString("multiverseid")
        } catch (e: JSONException) {
            null
        }
        this.names = try {
            json.getJSONArray("names").toString().split(",")
        } catch (e: JSONException) {
            null
        }
        this.manaCost = try {
            json.getString("manaCost")
        } catch (e: JSONException) {
            null
        }
        this.variations = try {
            json.getJSONArray("variations").toString().split(",")
        } catch (e: JSONException) {
            null
        }
        this.imageUrl = try {
            json.getString("imageUrl").replace("https", "http").replace("http", "https")
        } catch (e: JSONException) {
            null
        }
        this.watermark = try {
            json.getString("watermark")
        } catch (e: JSONException) {
            null
        }
        this.releaseDate = try {
            json.getString("releaseDate")
        } catch (e: JSONException) {
            null
        }

        val tempRulings = mutableMapOf<String, String>()
        try {
            val arr = json.getJSONArray("rulings")
            var obj: JSONObject
            for (i in 0..<arr.length()) {
                obj = arr.getJSONObject(i)
                tempRulings[obj.getString("date")] = obj.getString("text")
            }
            this.rulings = tempRulings
        } catch (e: JSONException) {
            this.rulings = null
        }

        this.originalText = try {
            json.getString("originalText")
        } catch (e: JSONException) {
            null
        }
        this.originalType = try {
            json.getString("originalType")
        } catch (e: JSONException) {
            null
        }

        val tempLegalities = mutableMapOf<String, String>()
        try {
            val arr = json.getJSONArray("legalities")
            var obj: JSONObject
            for (i in 0..<arr.length()) {
                obj = arr.getJSONObject(i)
                tempLegalities[obj.getString("format")] = obj.getString("legality")
            }
            this.legalities = tempLegalities
        } catch (e: JSONException) {
            this.legalities = null
        }

        cnt++
    }
}