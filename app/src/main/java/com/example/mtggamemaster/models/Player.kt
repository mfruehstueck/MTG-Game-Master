package com.example.mtggamemaster.models

data class Player(
    var id: String = "${cnt++}",
    var name: String,
    var life: Int = 40,
    var poison: Int = 0,
    var energy: Int = 0
) {
    fun update(player: Player) {
        this.name = player.name
        this.life = player.life
        this.poison = player.poison
        this.energy = player.energy
    }

    companion object {
        var cnt = 0
    }
}