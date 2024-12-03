package com.sabrina.rickAndMorty.data.api

import com.sabrina.rickAndMorty.data.dto.ResponseCharacters
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.sabrina.rickAndMorty.data.dto.Character


interface CharacteresApi {

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int): ResponseCharacters

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character

}