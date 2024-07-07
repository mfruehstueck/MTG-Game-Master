package com.example.mtggamemaster.viewmodels.player

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtggamemaster.data.card.MTGRepository
import com.example.mtggamemaster.models.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayersViewModel(
    private val repository: MTGRepository
) : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(mutableListOf())
    val players: StateFlow<List<Player>> = _players.asStateFlow()
    fun gainLife(playerID: String) {
//        viewModelScope.launch {
//            repository.getPlayers().distinctUntilChanged()
//                .collect { playerList ->
//                    playerList.map
//
//                    if (player.id == id) {
//                        player.copy(life = player.life + 1)
////                    repository.updatePlayer(player)
//                    } else {
//                        player
//                    }
//        }

        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.id == playerID) {
                    player.copy(life = player.life + 1)
//                    repository.updatePlayer(player)
                } else {
                    player
                }
            }
        }
//        var test = currentPlayers.find { checkPlayer -> checkPlayer.id == id }
    }

    fun gainEnergy(playerID: String) {
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

    fun gainPoison(playerID: String) {
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

    fun losePoison(playerID: String) {
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

    fun loseLife(playerID: String) {
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

    fun loseEnergy(playerID: String) {
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

    fun addPlayer(player: Player) {
        viewModelScope.launch {
            repository.addPlayer(player)
            _players.update { players ->
                players + player
            }
        }
    }

    fun removePlayer(player: Player) {
        _players.update { players ->
            players - player
        }
    }
    fun rollDie(): Int {
        return (1..6).random()
    }

    init {
        viewModelScope.launch {
            repository.getPlayers().distinctUntilChanged()
                .collect { playerList ->
                    if (playerList != null) {
                        _players.value = playerList
                    } else Log.i("MFR_DEBUG - PlayerViewModel init", "no players found")

                }
        }
    }
}