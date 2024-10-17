package com.example.ppm_lab06_pokeapi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "RFirst100") {
        composable("RFirst100") {
            RFirst100(navController = navController)
        }
        composable("MSpriteScreen/{pokemonName}/{pokemonId}") { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: "0"
            MSpriteScreen(pokemonName = pokemonName, pokemonId = pokemonId)
        }
    }
}
