package com.example.ppm_lab06_pokeapi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ppm_lab06_pokeapi.network.Pokemon

//@Composable
//fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
//    NavHost(navController = navController, startDestination = "RFirst100") {
//        composable("RFirst100") {
//            RFirst100(navController = navController)
//        }
//        composable("DetailFragment/{pokemonName}/{pokemonId}") { backStackEntry ->
//            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
//            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: "0"
//            val poke = Pokemon(name = pokemonName, url = "", id = pokemonId.toInt())
//
//            Detail(pokemon = poke)
//        }
//    }
//}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: "RFirst100"

    val title = when (currentScreen) {
        "RFirst100" -> "MainFragment"
        "DetailFragment/{pokemonName}/{pokemonId}" -> "DetailFragment"
        else -> "App Pokémon"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBar(
                title = title,
                navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){ innerPadding ->
        NavHost(navController = navController, startDestination = "RFirst100", modifier = modifier.padding(innerPadding)) {
            composable("RFirst100") {
                RFirst100(navController = navController)
            }
            composable("DetailFragment/{pokemonName}/{pokemonId}") { backStackEntry ->
                val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
                val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: "0"
                val poke = Pokemon(name = pokemonName, url = "", id = pokemonId.toInt())

                Detail(pokemon = poke)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, canNavigateBack: Boolean, navigateUp: () -> Unit = {}, modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        modifier = modifier
    )
}

