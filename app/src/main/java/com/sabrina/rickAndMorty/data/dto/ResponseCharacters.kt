package com.sabrina.rickAndMorty.data.dto

import com.google.gson.annotations.SerializedName


data class ResponseCharacters (
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("results")
    var list: MutableList<Character>,
    @SerializedName("total_pages")
    var totalPaginas: Int,
    @SerializedName("total_results")
    var totalResultados: Int
)
