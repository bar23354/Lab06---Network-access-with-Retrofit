package com.example.ppm_lab06_pokeapi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ppm_lab06_pokeapi.network.Pokemon
import com.example.ppm_lab06_pokeapi.network.RetrofitClient
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun RFirst100(navController: NavController) {
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val response = RetrofitClient.apiService.getPokemonList(100)
            pokemonList = response.results
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon = pokemon, onClick = {
                navController.navigate("MSpriteScreen/${pokemon.name}")
            })
        }
    }
}

@Composable
fun PokemonRow(pokemon: Pokemon, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.url.split("/").dropLast(1).last()}.png",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = pokemon.name)
    }
}