package com.sabrina.rickAndMorty.data.dto


data class Character(
    val id: Int,
    var image: String = "",
    var name: String = "",
    var species: String = "",
    var status: String = "",
    var gender: String = "",
    var origin: Info,
    var location: Info,
)

data class Info(
    val name: String = "",
    val url: String = ""
)
