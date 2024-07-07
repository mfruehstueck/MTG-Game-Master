package com.example.mtggamemaster.data.card

import com.example.mtggamemaster.data.card.api.MTGAPI
import com.example.mtggamemaster.models.Deck
import com.example.mtggamemaster.models.GameSession
import com.example.mtggamemaster.models.Player
import com.example.mtggamemaster.models.card.Card
import com.example.mtggamemaster.models.card.CardFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class MTGRepository(/*private val cardDAO: CardDAO*/) {

    //NSEC: cards
    fun card_add(card: Card) {
        if (!TempDatabase.cards.contains(card)) {
            TempDatabase.cards.add(card)
        }
    }

    fun card_update(card: Card) {
        val currentCard = TempDatabase.cards.find { checkCard -> checkCard.id == card.id }
            ?: throw NullPointerException()

        currentCard.update(card)
    }

    fun card_init(handleResponse: () -> Unit = {}) {
        if (TempDatabase.cards.size <= 5) {
            MTGAPI.queryCards("https://api.magicthegathering.io/v1/cards?set=UNF") { queriedCards ->
                queriedCards.forEach { queriedCard ->
                    card_add(queriedCard)
                }
                handleResponse()
            }
        } else handleResponse()
    }

    fun card_delete(card: Card): Nothing = throw NotImplementedError()
    fun card_getByID(id: String): Flow<Card?> =
        flowOf(TempDatabase.cards.find { card -> card.id == id })

    fun card_getFiltered(cardFilter: CardFilter): Flow<List<Card>?> {
        return flowOf(
            TempDatabase.cards.filter { card ->
                card.name?.lowercase()?.matches(cardFilter.name) ?: false && (
                        card.supertypes?.any { supertype ->
                            supertype.lowercase().matches(cardFilter.type)
                        } ?: false ||
                                card.type?.lowercase()?.matches(cardFilter.type) ?: false ||
                                card.subtypes?.any { subtype ->
                                    subtype.lowercase().matches(cardFilter.type)
                                } ?: false
                        ) &&
                        card.text?.lowercase()?.matches(cardFilter.text) ?: false && (
                        card.set?.lowercase()?.matches(cardFilter.set) ?: false ||
                                card.setName?.lowercase()?.matches(cardFilter.set) ?: false
                        ) &&
//                        card.manaCost?.toIntOrNull()?.compareTo(cardFilter.manacost.toIntOrNull()) == 0
                        card.colors?.any { color ->
                            cardFilter.colors.contains(
                                color
                                    .replace("\"", "")
                                    .replace("[", "")
                                    .replace("]", "")
                            )
                        } ?: false &&
                        card.rarity?.matches(cardFilter.rarity) ?: false
            }
        )
    }

    fun card_getSet(set: String): Nothing = throw NotImplementedError()
    fun card_getTypes(): Nothing = throw NotImplementedError()
    fun card_getSubTypes(): Nothing = throw NotImplementedError()
    fun card_getSuperTypes(): Nothing = throw NotImplementedError()
    fun card_getFormats(): Nothing = throw NotImplementedError()
    fun card_getFavorite(): Flow<List<Card>?> =
        flowOf(TempDatabase.cards.filter { card -> card.isFavorite })

    //NSEC: decks
    fun deck_add(deck: Deck) {
        TempDatabase.decks.add(deck)
    }

    fun deck_update(deck: Deck) {
        val currentDeck = TempDatabase.decks.find { foundDeck -> foundDeck.id == deck.id }
            ?: throw NullPointerException()

        currentDeck.update(deck)
    }

    fun deck_delete(deckID: String) = TempDatabase.decks.remove(
        TempDatabase.decks.find { foundDeck -> foundDeck.id == deckID }
    )

    fun deck_getByID(id: String): Flow<Deck?> =
        flowOf(TempDatabase.decks.find { deck -> deck.id == id })

    fun deck_getAll(): Flow<List<Deck>?> = flowOf(TempDatabase.decks)
    fun deck_getCards(deckID: String): Flow<List<Card>?> =
        flowOf(TempDatabase.decks.find { checkDeck ->
            checkDeck.id == deckID
        }?.cardList ?: emptyList())

    fun deck_getFavorite(): Flow<List<Deck>?> =
        flowOf(TempDatabase.decks.filter { deck -> deck.isFavorite })

    fun deck_addCard(deckID: String, cardID: String) {
        val foundDeck = TempDatabase.decks.find { checkDeck -> checkDeck.id == deckID }
            ?: throw NullPointerException()

        val foundCard = TempDatabase.cards.find { checkCard -> checkCard.id == cardID }
            ?: throw NullPointerException()

        foundDeck.cardList.add(foundCard)
    }

    fun deck_removeCard(deckID: String, cardID: String) {
        val foundDeck = TempDatabase.decks.find { checkDeck -> checkDeck.id == deckID }
            ?: throw NullPointerException()

        val foundCard = TempDatabase.cards.find { checkCard -> checkCard.id == cardID }
            ?: throw NullPointerException()

        foundDeck.cardList.remove(foundCard)
    }

    //NSEC: GameSessions

    fun gamesession_add(gamesession: GameSession) {
        if (!TempDatabase.gamesessions.contains(gamesession)) {
            TempDatabase.gamesessions.add(gamesession)
        }
    }

    fun gamesession_getAll(): Flow<List<GameSession>?> = flowOf(TempDatabase.gamesessions)
    fun gamesession_getByID(gamesessionID: String): Flow<GameSession?> =
        flowOf(TempDatabase.gamesessions.find { check -> check.id == gamesessionID })

    fun gamesession_addPlayer(gamesessionID: String, player: Player) {
        val foundGamesession = TempDatabase.gamesessions.find { check ->
            check.id == gamesessionID
        } ?: return

        foundGamesession.players.add(player)
    }

    fun gamesession_getPlayerByID(gamesessionID: String, playerID: String): Flow<Player?> {
        val foundGamesession = TempDatabase.gamesessions.find { check ->
            check.id == gamesessionID
        } ?: return emptyFlow()

        val foundPlayer = foundGamesession.players.find { check ->
            check.id == playerID
        } ?: return emptyFlow()

        return flowOf(foundPlayer)
    }

    fun gamesession_updatePlayer(gamesessionID: String, player: Player) {
        val foundGamesession = TempDatabase.gamesessions.find { check ->
            check.id == gamesessionID
        } ?: return

        val foundPlayer = foundGamesession.players.find { check ->
            check.id == player.id
        } ?: return

        foundPlayer.update(player)
    }

    fun gamesession_getPlayers(gamesessionID: String): Flow<List<Player>?> {
        val foundGamesession = TempDatabase.gamesessions.find { check ->
            check.id == gamesessionID
        } ?: return emptyFlow()

        return flowOf(foundGamesession.players)
    }

    fun gamesession_removePlayer(gamesessionID: String, player: Player) {
        val foundGamesession = TempDatabase.gamesessions.find { check ->
            check.id == gamesessionID
        } ?: return

        foundGamesession.players.remove(player)
    }


    //NSEC: Player
    fun addPlayer(player: Player) {
        TempDatabase.players.add(player)
    }

    fun updatePlayer(player: Player) {
        val currentPlayer = TempDatabase.players.find { checkPlayer -> checkPlayer.id == player.id }
            ?: throw NullPointerException()

        currentPlayer.update(player)
    }

    fun getPlayers(): Flow<List<Player>?> {
        val players: MutableSet<Player> = mutableSetOf()

        TempDatabase.gamesessions.forEach { gamesession ->
            players.addAll(gamesession.players)
        }

        return flowOf(players.toList())
    }


    companion object {
        var firstRun = true

        fun getSampleDeckList(cnt: Int): MutableList<Deck> {
            val deckList = mutableListOf<Deck>()

            for (i in 0..cnt) {
                deckList.add(
                    Deck(
                        id = "$i",
                        name = "Deck $i",
                        cardList = mutableListOf()
                    )
                )
            }

            return deckList
        }
    }
}