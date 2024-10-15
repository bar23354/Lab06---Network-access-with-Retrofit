package com.example.ppm_lab06_pokeapi.network

data class PokeResponse(val results: List<Pokemon>)

// Modelo para el Pokémon (simplificado)
data class Pokemon(
    val name: String,
    val url: String
)