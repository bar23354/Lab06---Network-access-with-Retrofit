package com.example.ppm_lab06_pokeapi

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ppm_lab06_pokeapi.network.Pokemon
import com.example.ppm_lab06_pokeapi.network.RetrofitClient
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun RFirst100(navController: NavController) {
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitClient.apiService.getPokemonList(100)
                pokemonList = response.results
            } catch (e: Exception) {
            }
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
    val pokemonId = pokemon.url.split("/").dropLast(1).last()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_pokemon),
            contentDescription = "Placeholder",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = pokemon.name)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRFirst100() {
    val dummyPokemonList = listOf(
        Pokemon(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
        Pokemon(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/"),
        Pokemon(name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/")
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(dummyPokemonList) { pokemon ->
            PokemonRow(pokemon = pokemon, onClick = {})
        }
    }
}
