package com.example.mtggamemaster.data

class SetURL {
    var requestURL: String = ""

    var name: String = ""
    var block: String = ""

    constructor()
    constructor(name: String, block: String, requestURL: String = "") : this() {
        this.requestURL = requestURL
        this.name = name
        this.block = block
    }

    @Override
    override fun toString(): String {
        return (requestURL +
                name +
                block).replaceFirst("&", "?")
    }
}