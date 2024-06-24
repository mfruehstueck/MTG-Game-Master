package com.example.mtggamemaster.data

import com.example.mtggamemaster.CardEndpoints
import com.example.mtggamemaster.ENV

class URLBuilder {
    var requestURL: String = ""

    //NSEC: /cards
    var nameCard: String = ""
    var layout: String = ""
    var cmc: String = ""
    var colors: String = ""
    var colorIdentity: String = ""
    var type: String = ""
    var supertypes: String = ""
    var types: String = ""
    var subtypes: String = ""
    var rarity: String = ""
    var set: String = ""
    var setName: String = ""
    var text: String = ""
    var flavor: String = ""
    var artist: String = ""
    var number: String = ""
    var power: String = ""
    var toughness: String = ""
    var loyalty: String = ""
    var language: String = ""
    var gameFormat: String = ""
    var legality: String = ""
    var page: String = ""
    var pageSize: String = ""
    var orderBy: String = ""
    var random: String = ""
    var contains: String = ""
    var id: String = ""
    var multiverseid: String = ""

    //NSEC: /sets
    var nameSet: String = ""
    var block: String = ""

    fun requestURL(requestURL: String): URLBuilder {
        this.requestURL = requestURL
        return this
    }

    fun nameCard(card_names: String): URLBuilder {
        //N: card_names expects logical 'or' | in string of card_names, eg "name1|name2"
        //N: card_names can have whitespaces
        if (card_names.isNotEmpty()) {
            this.nameCard = "&name=" + card_names.replace(" ", "")
        }
        return this
    }

    fun layout(layout: String): URLBuilder {
        if (layout.isNotEmpty()) {
            this.nameCard = "&layout=" + layout.replace(" ", "")
        }
        return this
    }

    fun cmc(cmc: String): URLBuilder {
        if (cmc.isNotEmpty()) {
            this.cmc = "&cmc=" + cmc.replace(" ", "")
        }
        return this
    }

    fun id(id: String): URLBuilder {
        if (id.isNotEmpty()) {
            this.id = "&id=" + id.replace(" ", "")
        }
        return this
    }

    fun multiverseid(multiverseid: String): URLBuilder {
        if (multiverseid.isNotEmpty()) {
            this.multiverseid = "&multiverseid=" + multiverseid.replace(" ", "")
        }
        return this
    }

    fun buildCardURL(): CardURL {
        return CardURL(
            name = nameCard,
            layout = layout,
            cmc = cmc,
            colors = colors,
            colorIdentity = colorIdentity,
            type = type,
            supertypes = supertypes,
            types = types,
            subtypes = subtypes,
            rarity = rarity,
            set = set,
            setName = setName,
            text = text,
            flavor = flavor,
            artist = artist,
            number = number,
            power = power,
            toughness = toughness,
            loyalty = loyalty,
            language = language,
            gameFormat = gameFormat,
            legality = legality,
            page = page,
            pageSize = pageSize,
            orderBy = orderBy,
            random = random,
            contains = contains,
            id = id,
            multiverseid = multiverseid,
            requestURL = "${ENV.CARD_ENDPOINTS[CardEndpoints.cards]}"
        )
    }

    fun buildSetURL(): SetURL {
        return SetURL(
            name = nameSet,
            block = block,
            requestURL = "${ENV.CARD_ENDPOINTS[CardEndpoints.sets]}"
        )
    }
}