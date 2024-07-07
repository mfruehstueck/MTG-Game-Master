package com.example.mtggamemaster.models

class GameSession() {
    var id: String = cnt++.toString()
    var players: MutableList<Player> = mutableListOf()
    var winner: Player? = null

    companion object {
        private var cnt = 0
    }
}