package com.example.mtggamemaster.models.card


//N: CardFilter is based on builder-pattern
//N: fields are regex strings
class CardFilter {
    var name: Regex = Regex(".*")

    //N: type includes type, sub- and supertypes
    var type: Regex = Regex(".*")

    var text: Regex = Regex(".*")

    var set: Regex = Regex(".*")

    var manacost: String = ""
    var power: String = ""
    var toughness: String = ""

    var colors: MutableList<String> = mutableListOf(
        "W",
        "U",
        "B",
        "R",
        "G",
        "C"
    )

    var rarity: Regex = Regex(".*")
    var rarityList: MutableList<String> = mutableListOf(
        "Common",
        "Uncommon",
        "Rare",
        "Mythic"
    )

    private fun convertToRegex(str: String): Regex {
        val searchPhrase = str.lowercase()
        var out: String = ""
        val and: MutableList<String>
        val or: MutableList<MutableList<String>> = mutableListOf()

        and = str.split(",").toMutableList()
        and.forEach {
            or.add(it.split("|").toMutableList())
        }

        return Regex(".*${searchPhrase}.*")
    }

    fun name(name: String): CardFilter {
        if (name.isNotEmpty()) {
            this.name = convertToRegex(name)
        } else this.name = Regex(".*")
        return this
    }

    fun type(type: String): CardFilter {
        if (type.isNotEmpty()) {
            this.type = convertToRegex(type)
        } else this.type = Regex(".*")
        return this
    }

    fun text(text: String): CardFilter {
        if (text.isNotEmpty()) {
            this.text = convertToRegex(text)
        } else this.text = Regex(".*")
        return this
    }

    fun set(set: String): CardFilter {
        if (set.isNotEmpty()) {
            this.set = convertToRegex(set)
        } else this.set = Regex("unf")
        return this
    }

    fun colors(color: String, checked: Boolean): CardFilter {
        val firstLetter = if (color == "Blue") {
            "U"
        } else {
            color.first()
        }

        if (firstLetter.toString().isNotEmpty()) {

            if (checked) {
                if (this.colors.find { str -> str == firstLetter.toString() } == null) {
                    this.colors.add(firstLetter.toString())
                }
            } else {
                if (this.colors.find { str -> str == firstLetter.toString() } != null) {
                    this.colors.remove(firstLetter.toString())
                }
            }

        } else this.colors = mutableListOf(
            "W",
            "U",
            "B",
            "R",
            "G",
            "C"
        )
        return this
    }

    fun rarity(rarity: String, checked: Boolean): CardFilter {
        if (rarity.isNotEmpty()) {
            if (checked) {
                this.rarityList.add(rarity)
            } else this.rarityList.remove(rarity)
            var str = ""
            this.rarityList.forEach {
                str += "$it|"
            }
            this.rarity = Regex(str.removeSuffix("|"))
        } else this.rarity = Regex(".*")
        return this
    }
}