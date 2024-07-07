package com.example.mtggamemaster.models

class GameSession() {
    var id: String = cnt++.toString()
    var players: MutableList<Player> = mutableListOf()
    var winner: Player? = null
    var startingLife: Int = 30

    fun setStartingLif(startingLife: Int) {
        this.startingLife = startingLife
    }

    companion object {
        private var cnt = 0
    }
}