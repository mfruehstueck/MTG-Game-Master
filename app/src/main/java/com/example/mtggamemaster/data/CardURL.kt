package com.example.mtggamemaster.data

class CardURL {
    var requestURL: String = ""

    //NSEC: /cards
    var name: String = ""
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

    constructor()
    constructor(
        name: String,
        layout: String,
        cmc: String,
        colors: String,
        colorIdentity: String,
        type: String,
        supertypes: String,
        types: String,
        subtypes: String,
        rarity: String,
        set: String,
        setName: String,
        text: String,
        flavor: String,
        artist: String,
        number: String,
        power: String,
        toughness: String,
        loyalty: String,
        language: String,
        gameFormat: String,
        legality: String,
        page: String,
        pageSize: String,
        orderBy: String,
        random: String,
        contains: String,
        id: String,
        multiverseid: String,

        requestURL: String = ""
    ) : this() {
        this.name = name
        this.layout = layout
        this.cmc = cmc
        this.colors = colors
        this.colorIdentity = colorIdentity
        this.type = type
        this.supertypes = supertypes
        this.types = types
        this.subtypes = subtypes
        this.rarity = rarity
        this.set = set
        this.setName = setName
        this.text = text
        this.flavor = flavor
        this.artist = artist
        this.number = number
        this.power = power
        this.toughness = toughness
        this.loyalty = loyalty
        this.language = language
        this.gameFormat = gameFormat
        this.legality = legality
        this.page = page
        this.pageSize = pageSize
        this.orderBy = orderBy
        this.random = random
        this.contains = contains
        this.id = id
        this.multiverseid = multiverseid

        this.requestURL = requestURL
    }

    @Override
    override fun toString(): String {
        return (requestURL +
                name +
                layout +
                cmc +
                colors +
                colorIdentity +
                type +
                supertypes +
                types +
                subtypes +
                rarity +
                set +
                setName +
                text +
                flavor +
                artist +
                number +
                power +
                toughness +
                loyalty +
                language +
                gameFormat +
                legality +
                page +
                pageSize +
                orderBy +
                random +
                contains +
                id +
                multiverseid).replaceFirst("&", "?")
    }

    //TODO: for "Find by name" with or without foreign name
    //lateinit var name: String
    //lateinit var language: String
}