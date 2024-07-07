package com.example.mtggamemaster.viewmodels.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.data.card.TempDatabase
import com.example.mtggamemaster.models.GameSession
import com.example.mtggamemaster.models.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayersViewModel(
    private val repository: MTGRepository
) : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(mutableListOf())
    val players: StateFlow<List<Player>> = _players.asStateFlow()

    fun add() {
        viewModelScope.launch {
            repository.gamesession_add(GameSession())
        }
    }

    fun getByID(gamesessionID: String): GameSession? {
        var gamesession: GameSession? = null

        viewModelScope.launch {
            repository.gamesession_getByID(gamesessionID).collect {
                gamesession = it
            }
        }

        return gamesession
    }

    fun getAll(): List<GameSession>? {
        var gamesessions: List<GameSession>? = null

        viewModelScope.launch {
            repository.gamesession_getAll().collect {
                gamesessions = it
            }
        }

        return gamesessions
    }

    fun gainLife(gamesessionID: String, playerID: String) {
        viewModelScope.launch {
            repository.gamesession_getPlayerByID(gamesessionID, playerID).collect { player ->
                if (player != null) {
                    repository.gamesession_updatePlayer(gamesessionID, player.updateLife(1))
                }
            }
        }

        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.id == playerID) {
                    player.copy(life = player.life + 1)
                } else {
                    player
                }
            }
        }
    }

    fun gainEnergy(gamesessionID: String, playerID: String) {
        viewModelScope.launch {
            repository.gamesession_getPlayerByID(gamesessionID, playerID).collect { player ->
                if (player != null) {
                    repository.gamesession_updatePlayer(gamesessionID, player.updateEnergy(1))
                }
            }
        }

        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.id == playerID) {
                    player.copy(energy = player.energy + 1)
                } else {
                    player
                }
            }
        }
    }

    fun gainPoison(gamesessionID: String, playerID: String) {
        viewModelScope.launch {
            repository.gamesession_getPlayerByID(gamesessionID, playerID).collect { player ->
                if (player != null) {
                    repository.gamesession_updatePlayer(gamesessionID, player.updatePoison(1))
                }
            }
        }

        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.id == playerID) {
                    player.copy(poison = player.poison + 1)
                } else {
                    player
                }
            }
        }
    }

    fun losePoison(gamesessionID: String, playerID: String) {
        viewModelScope.launch {
            repository.gamesession_getPlayerByID(gamesessionID, playerID).collect { player ->
                if (player != null) {
                    repository.gamesession_updatePlayer(gamesessionID, player.updatePoison(-1))
                }
            }
        }

        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.id == playerID) {
                    player.copy(poison = player.poison - 1)
                } else {
                    player
                }
            }
        }
    }

    fun loseLife(gamesessionID: String, playerID: String) {
        viewModelScope.launch {
            repository.gamesession_getPlayerByID(gamesessionID, playerID).collect { player ->
                if (player != null) {
                    repository.gamesession_updatePlayer(gamesessionID, player.updateLife(-1))
                }
            }
        }

        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.id == playerID) {
                    player.copy(life = player.life - 1)
                } else {
                    player
                }
            }
        }
    }

    fun loseEnergy(gamesessionID: String, playerID: String) {
        viewModelScope.launch {
            repository.gamesession_getPlayerByID(gamesessionID, playerID).collect { player ->
                if (player != null) {
                    repository.gamesession_updatePlayer(gamesessionID, player.updateEnergy(-1))
                }
            }
        }

        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.id == playerID) {
                    player.copy(energy = player.energy - 1)
                } else {
                    player
                }
            }
        }
    }

    fun addPlayer(gamesessionID: String, playerName: String) {
        var player: Player? = null

        viewModelScope.launch {
            repository.getPlayers().collect { players ->
                player = players?.find { player -> player.name == playerName }
                if (player == null) {
                    player = Player(name = playerName)
                }
            }
            repository.gamesession_addPlayer(gamesessionID, player!!)
            _players.update { players ->
                players + player!!
            }
        }
    }

    fun removePlayer(gamesessionID: String, player: Player) {
        viewModelScope.launch {
            repository.gamesession_removePlayer(gamesessionID, player)
            _players.update { players ->
                players - player
            }
        }
    }

    fun rollDie(): Int {
        return (1..6).random()
    }

    init {
        viewModelScope.launch {
            repository.gamesession_getPlayers("${TempDatabase.gamesessions.size - 1}")
                .collect { playerList ->
                    if (playerList != null) {
                        _players.value = playerList
                    } else Log.i("MFR_DEBUG - PlayerViewModel init", "no players found")
                }
        }
    }
}