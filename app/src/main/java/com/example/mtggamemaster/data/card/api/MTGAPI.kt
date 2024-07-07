package com.example.mtggamemaster.data.card.api

import android.util.Log
import com.example.mtggamemaster.models.card.Card
import com.example.mtggamemaster.models.card.Set
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class MTGAPI {
    lateinit var sets: List<Set>
    lateinit var types: List<String>
    lateinit var subtypes: List<String>
    lateinit var supertypes: List<String>
    lateinit var formats: List<String>

    companion object {
        private val client = OkHttpClient()

        fun queryCards(url: String, handleResponse: (List<Card>, Headers) -> Unit = { _: List<Card>, _: Headers -> }) {
            httpRequest(url) { resp, headers ->
                val jsonArray = JSONObject(resp).getJSONArray("cards")
                val cards = mutableListOf<Card>()

                for (i in 0..<jsonArray.length()) {
                    try {
                        val card = Card(jsonArray.getJSONObject(i))
                        cards.add(card)
                    } catch (e: JSONException) {
                        println("$i. obj in arr: $e")
                    }
                }

                handleResponse(cards.toList(), headers)
            }
        }

        fun querySets(url: String, handleResponse: (String) -> Unit = {}) {
        }

        private fun httpRequest(
            url: String,
            handleResponse: (String, Headers) -> Unit = { _: String, _: Headers -> }
        ) {
            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.i("", "pagesize = ${response.headers["Page-Size"]}")
                    Log.i("", "count = ${response.headers["Count"]}")
                    Log.i("", "totalcount = ${response.headers["Total-Count"]}")
                    Log.i("", "requestsleft = ${response.headers["Ratelimit-Remaining"]}")
                    response.use {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        handleResponse(response.body!!.string(), response.headers)
                    }
                }
            })
        }

        fun getSampleCardList(cnt: Int): MutableList<Card> {
            val cardList = mutableListOf<Card>()

            cardList.add(
                Card(
                    name = "Narset, Enlightened Master",
                    layout = "normal",
                    cmc = 6,
                    colors = listOf("Red", "Blue", "White"),
                    colorIdentity = listOf("R", "U", "W"),
                    type = "Legendary Creature — Human Monk",
                    supertypes = listOf("Legendary"),
                    types = listOf("Creature"),
                    subtypes = listOf("Human", "Monk"),
                    rarity = "Mythic",
                    set = "KTK",
                    setName = "Khans of Tarkir",
                    text = "First strike, hexproof\nWhenever Narset, Enlightened Master attacks, exile the top four cards of your library. Until end of turn, you may cast noncreature spells from among those cards without paying their mana costs.",
                    flavor = null,
                    artist = "Magali Villeneuve",
                    number = "190",
                    power = "3",
                    toughness = "2",
                    loyality = null,
                    multiverseid = "386616",

                    names = null,
                    manaCost = "{3}{U}{R}{W}",
                    variations = listOf("NOTSET"),
                    imageUrl = "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=386616&type=card",
                    watermark = "jeskai",
                    releaseDate = null,
                    rulings = mapOf(
                        Pair("2014-09-20", "Any exiled cards you don’t cast remain in exile."),
                        Pair(
                            "2014-09-20",
                            "Because you’re already casting the card using an alternative cost (by casting it without paying its mana cost), you can’t pay any other alternative costs for the card, including casting it face down using the morph ability. You can pay additional costs, such as kicker costs. If the card has any mandatory additional costs, you must pay those."
                        ),
                        Pair(
                            "2014-09-20",
                            "If the card has {X} in its mana cost, you must choose 0 as the value for X when casting it."
                        ),
                        Pair(
                            "2014-09-20",
                            "The cards are exiled face up. Casting the noncreature cards exiled this way follows the normal rules for casting those cards. You must follow all applicable timing rules. For example, if one of the exiled cards is a sorcery card, you can cast it only during your main phase while the stack is empty."
                        ),
                        Pair("2014-09-20", "You can’t play any land cards exiled with Narset.")
                    ),
                    printings = listOf("CMM", "KTK", "PKTK", "SLD"),
                    originalText = "First strike, hexproof\nWhenever Narset, Enlightened Master attacks, exile the top four cards of your library. Until end of turn, you may cast noncreature cards exiled with Narset this turn without paying their mana costs.",
                    originalType = "Legendary Creature — Human Monk",
                    legalities = mapOf(
                        Pair("Brawl", "Legal"),
                        Pair("Commander", "Legal"),
                        Pair("Duel", "Legal"),
                        Pair("Explorer", "Legal"),
                        Pair("Gladiator", "Legal"),
                        Pair("Historic", "Legal"),
                        Pair("Legacy", "Legal"),
                        Pair("Modern", "Legal"),
                        Pair("Oathbreaker", "Legal"),
                        Pair("Penny", "Legal"),
                        Pair("Pioneer", "Legal"),
                        Pair("Timeless", "Legal"),
                        Pair("Vintage", "Legal"),
                    ),
                    id = "15e45fe0-92ea-52dc-8665-7105ac30db70"
                )
            )

            for (i in 0..cnt) {
                cardList.add(
                    Card(
                        name = "Card $i",
                        layout = "normal",
                        cmc = 1,
                        colors = listOf("White", "Blue"),
                        colorIdentity = listOf("W", "B"),
                        type = "Supertype $i Type $i - Subtype $i",
                        supertypes = listOf("Supertype $i"),
                        types = listOf("Type $i"),
                        subtypes = listOf("Subtype $i"),
                        rarity = "mythic",
                        set = "S$i",
                        setName = "Set $i",
                        text = "Card $i",
                        flavor = "Flavor $i",
                        artist = "Artist $i",
                        number = "$i",
                        power = "$i",
                        toughness = "$i",
                        loyality = "$i",
                        multiverseid = "$i",

                        names = listOf("Name $i"),
                        manaCost = "{1}{W}",
                        variations = listOf("Variation $i"),
                        imageUrl = "https://www.1999.co.jp/itbig99/10995194a.jpg",
                        watermark = "Watermark $i",
                        releaseDate = "01.01.0001",
                        rulings = mapOf(Pair("Date $i", "Ruling $i")),
                        printings = listOf("Printing $i"),
                        originalText = "Original Text $i",
                        originalType = "Original Type $i",
                        legalities = mapOf(Pair("Format $i", "Legality $i")),
                        id = "$i"
                    )
                )
            }

            return cardList
        }
    }
}