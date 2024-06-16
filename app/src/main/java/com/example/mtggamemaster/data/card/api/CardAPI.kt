package com.example.mtggamemaster.data.card.api

import com.example.mtggamemaster.models.card.Card
import com.example.mtggamemaster.models.card.Set


class CardAPI {
    lateinit var sets: List<Set>
    lateinit var types: List<String>
    lateinit var subtypes: List<String>
    lateinit var supertypes: List<String>
    lateinit var formats: List<String>

//    fun getCardByID(cardID: String): Card? {
//        var test = getRequest(endpoint = CardEndpoints.cards, query = cardID, callback = object : Callback {
//            @Override
//            override fun onResponse(call: Call, response: Response) {
//                if (response.isSuccessful && response.body != null) {
//                    val responseData = response.body!!.string()
//                    TempDatabase.cards.addAll(responseParser(responseData))
//                    Log.i("MFR_DEBUG - getCardByID", "http: $responseData")
//                } else{
//                    Log.i("MFR_DEBUG - getCardByID", "request failed")
//                }
//            }
//
//            @Override
//            override fun onFailure(call: Call, e: IOException) {
//                throw e
//            }
//        })
//
//        while (!test.isExecuted()) {
//            Log.i("MFR_DEBUG - getCardByID", "wait for request")
//        }

//        val scope = CoroutineScope(Dispatchers.IO).launch {
//            Log.i("MFR_DEBUG - getCardByID", "started coroutine")
//
//            TempDatabase.cards.add(responseParser())
//        }

//        return null
//        Log.i("MFR_DEBUG - getCardByID", "jsonString: $resp")
//
//        return try {
//            responseParser(resp)
//        } catch (e: Exception) {
//            Log.i("MFR_DEBUG - getCardByID", "error: ${e.message}")
//            getSampleCardList(0)[0]
//        }

//    }

//    private fun getRequest(endpoint: CardEndpoints, query: String = "", callback: Callback): Call {
//        val client = OkHttpClient()
//        val getRequest = Request.Builder()
//            .url("$API_URL/$endpoint/$query")
//            .build()
//
//        val call = client.newCall(getRequest)
//        call.enqueue(callback)
//        return call
//
//
//        var resp = ""
//
//
//
//        val call = client.newCall(getRequest)
//        val response = call.execute()
//        assert(response.code == 200)
//        if (response.isSuccessful && response.body != null) {
//            val responseData = response.body!!.string()
//            resp = responseData
//            Log.i("MFR_DEBUG - requestGenerator", "http: $responseData")
//        }
//
//
////        TODO: replace with async version below
//        client.newCall(getRequest).enqueue(object : Callback {
//            @Override
//            override fun onResponse(call: Call, response: Response) {
//                if (response.isSuccessful && response.body != null) {
//                    val responseData = response.body!!.string()
//                    resp = responseData
//                    Log.i("MFR_DEBUG - requestGenerator", "http: $responseData")
//                }
//            }
//
//            @Override
//            override fun onFailure(call: Call, e: IOException) {
//                throw e
//            }
//        })
//    }

//    suspend fun makeRequest(endpoint: CardEndpoints, query: String = ""): String =
//        suspendCoroutine { continuation ->
//            val client = OkHttpClient()
//            val getRequest = Request.Builder()
//                .url("$API_URL/$endpoint/$query")
//                .build()
//
//            client.newCall(getRequest).enqueue(object : Callback {
//                @Override
//                override fun onResponse(call: Call, response: Response) {
//                    if (response.isSuccessful && response.body != null) {
//                        response.use {
//                            continuation.resume(response.body!!.string())
//                        }
//                    }
//                }
//
//                @Override
//                override fun onFailure(call: Call, e: IOException) {
//                    continuation.resumeWithException(e)
//                    e.printStackTrace()
//                }
//            })
//        }

//    private fun responseParser(jsonString: String): List<Card> {
//        val jsonObject = JSONObject(jsonString)
//        val cardObject = jsonObject.getJSONObject("card")
//
//        val gson = Gson()
//
//        return try {
//            gson.fromJson(jsonString, listOf<Card>()::class.java).toList()
//        } catch (e: Exception) {
//            emptyList()
//        }

//        return Card(
//            name = cardObject.getString("name"),
//            manaCost = cardObject.getString("name"),
//            cmc = cardObject.getString("cmc").toFloat(),
//            colors = cardObject.getString("colors").split(","),
//            colorIdentity = cardObject.getString("colorIdentity").split(","),
//            type = cardObject.getString("type"),
//            supertypes = cardObject.getString("supertypes").split(","),
//            types = cardObject.getString("types").split(","),
//            subtypes = cardObject.getString("subtypes").split(","),
//            rarity = cardObject.getString("rarity"),
//            set = cardObject.getString("set"),
//            setName = cardObject.getString("setName"),
//            text = cardObject.getString("text"),
//            artist = cardObject.getString("artist"),
//            number = cardObject.getString("number").toInt(),
//            power = cardObject.getString("power").toInt(),
//            toughness = cardObject.getString("toughness").toInt(),
//            layout = cardObject.getString("layout"),
//            multiverseid = cardObject.getString("multiverseid"),
//            imageUrl = cardObject.getString("imageUrl"),
//            watermark = cardObject.getString("watermark"),
////            rulings = jsonObject.getString("rulings"),
//            variations = cardObject.getString("variations").split(","),
//            printings = cardObject.getString("printings").split(","),
//            originalText = cardObject.getString("originalText"),
//            originalType = cardObject.getString("originalType"),
////            legalities = jsonObject.getString("legalities"),
//            id = cardObject.getString("id"),
//        )
//    }

    companion object {
        fun getSampleCardList(cnt: Int):  MutableList<Card> {
            val cardList = mutableListOf(
                Card(
                    name = "Card 1",
                    layout = "normal",
                    cmc = 1,
                    colors = listOf("W", "B"),
                    colorIdentity = listOf("W", "B"),
                    type = "Supertype 1 Type 1 - Subtype 1",
                    supertypes = listOf("Supertype 1"),
                    types = listOf("Type 1"),
                    subtypes = listOf("Subtype 1"),
                    rarity = "mythic",
                    set = "S1",
                    setName = "Set 1",
                    text = "Card 1",
                    flavor = "Flavor 1",
                    artist = "Artist 1",
                    number = "1",
                    power = "1",
                    toughness = "1",
                    loyality = "1",
                    multiverseid = "1",

                    names = listOf("Name 1"),
                    manaCost = "{1}{W}",
                    variations = listOf("Variation 1"),
                    imageUrl = "https://www.1999.co.jp/itbig99/10995194a.jpg",
                    watermark = "Watermark 1",
                    releaseDate = "01.01.0001",
                    rulings = mapOf(Pair("Date 1", "Ruling 1")),
                    printings = listOf("Printing 1"),
                    originalText = "Original Text 1",
                    originalType = "Original Type 1",
                    legalities = mapOf(Pair("Format 1", "Legality 1")),
                    id = "1"
                ),
                Card(
                    name = "Narset, Enlightened Master",
                    layout = "normal",
                    cmc = 6,
                    colors = listOf("R", "U", "W"),
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
                ),
                Card(
                    name = "Card 2",
                    layout = "normal",
                    cmc = 2,
                    colors = listOf("W", "B"),
                    colorIdentity = listOf("W", "B"),
                    type = "Supertype 2 Type 2 - Subtype 2",
                    supertypes = listOf("Supertype 2"),
                    types = listOf("Type 2"),
                    subtypes = listOf("Subtype 2"),
                    rarity = "mythic",
                    set = "S2",
                    setName = "Set 2",
                    text = "Card 2",
                    flavor = "Flavor 2",
                    artist = "Artist 2",
                    number = "2",
                    power = "2",
                    toughness = "2",
                    loyality = "2",
                    multiverseid = "2",

                    names = listOf("Name 2"),
                    manaCost = "{1}{W}",
                    variations = listOf("Variation 2"),
                    imageUrl = "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=409741&type=card",
                    watermark = "Watermark 2",
                    releaseDate = "02.02.0002",
                    rulings = mapOf(Pair("Date 2", "Ruling 2")),
                    printings = listOf("Printing 2"),
                    originalText = "Original Text 2",
                    originalType = "Original Type 2",
                    legalities = mapOf(Pair("Format 2", "Legality 2")),
                    id = "2"
                )
            )

            for (i in 1..cnt)
                cardList.addAll(cardList)

            return cardList
        }
    }
}