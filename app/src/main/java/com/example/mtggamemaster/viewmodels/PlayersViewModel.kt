package com.example.mtggamemaster.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mtggamemaster.models.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Schould probably be renamed to GameSessionModel
class PlayersViewModel : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(mutableListOf())
    val players: StateFlow<List<Player>> = _players.asStateFlow()
    fun gainLife(playerName: String) {
        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.name == playerName) {
                    player.copy(life = player.life + 1)
                } else {
                    player
                }
            }
        }
    }
    fun gainEnergy(playerName: String) {
        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.name == playerName) {
                    player.copy(energy = player.energy + 1)
                } else {
                    player
                }
            }
        }
    }
    fun gainPoison(playerName: String) {
        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.name == playerName) {
                    player.copy(poison = player.poison + 1)
                } else {
                    player
                }
            }
        }
    }
    fun loseLife(playerName: String) {
        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.name == playerName) {
                    player.copy(life = player.life - 1)
                } else {
                    player
                }
            }
        }
    }
    fun loseEnergy(playerName: String) {
        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.name == playerName) {
                    player.copy(energy = player.energy - 1)
                } else {
                    player
                }
            }
        }
    }
    fun losePoison(playerName: String) {
        _players.update { currentPlayers ->
            currentPlayers.map { player ->
                if (player.name == playerName) {
                    player.copy(poison = player.poison - 1)
                } else {
                    player
                }
            }
        }
    }
    fun addPlayer(player: Player) {
        _players.update { players ->
            players+player
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
}