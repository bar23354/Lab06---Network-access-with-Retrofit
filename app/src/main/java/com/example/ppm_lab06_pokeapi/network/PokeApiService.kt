package com.example.ppm_lab06_pokeapi.network

import retrofit2.http.GET
import retrofit2.http.Query

// Definir la interfaz de PokeAPI
interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokeResponse
}