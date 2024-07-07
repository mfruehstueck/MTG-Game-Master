package com.example.mtggamemaster.models

data class Player(
    var id: String = "${cnt++}",
    var name: String,
    var life: Int = 40,
    var poison: Int = 0,
    var energy: Int = 0,
    var wins: Int = 0
) {
    fun update(player: Player) {
        this.name = player.name
        this.life = player.life
        this.poison = player.poison
        this.energy = player.energy
        this.wins = player.wins
    }

    fun updateLife(life: Int): Player {
        this.life += life
        return this
    }

    fun updateEnergy(energy: Int): Player {
        this.energy += energy
        return this
    }
    fun updatePoison(poison: Int): Player {
        this.poison += poison
        return this
    }

    companion object {
        var cnt = 0
    }
}