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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ppm_lab06_pokeapi.network.Pokemon
import com.example.ppm_lab06_pokeapi.network.RetrofitClient
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

// Composable que muestra la lista de los primeros 100 Pokémon.
@Composable
fun RFirst100(navController: NavController) {
    // Almacenar la lista de Pokémon
    var pokemonList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }

    // Recordamos un CoroutineScope para realizar operaciones asíncronas.
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                // Realiza la llamada a la API para obtener la lista de Pokémon.
                val response = RetrofitClient.apiService.getPokemonList(100)
                // Almacena la lista de resultados en el estado pokemonList.
                pokemonList = response.results
            } catch (e: Exception) {
                // Si ocurre un error, lo capturamos (en este caso, no hacemos nada).
            }
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // Itera sobre cada Pokémon en la lista y llama a PokemonRow para mostrar cada uno.
        items(pokemonList) { pokemon ->
            // Extrae el ID del Pokémon desde su URL.
            val pokemonId = pokemon.url.split("/").dropLast(1).last()
            // Muestra una fila con la información del Pokémon. Navega a la pantalla de detalles al hacer clic.
            PokemonRow(pokemon = pokemon, onClick = {
                navController.navigate("DetailFragment/${pokemon.name}/$pokemonId")
            })
        }
    }
}

@Composable
fun PokemonRow(pokemon: Pokemon, onClick: () -> Unit) {
    // Extrae el ID del Pokémon desde su URL.
    val pokemonId = pokemon.url.split("/").dropLast(1).last()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Muestra la imagen del Pokémon usando GlideImage con la URL del sprite.
        GlideImage(
            imageModel = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png",
            placeHolder = painterResource(id = R.drawable.placeholder_pokemon), // Imagen de marcador de posición.
            error = painterResource(id = R.drawable.placeholder_pokemon), // Imagen de error en caso de fallo.
            contentScale = ContentScale.Crop, // Escala la imagen para que ocupe el área sin distorsionarse.
            modifier = Modifier.size(64.dp) // Define el tamaño de la imagen.
        )
        Spacer(modifier = Modifier.width(16.dp)) // Espacio entre la imagen y el texto.
        // Muestra el nombre del Pokémon.
        Text(text = pokemon.name)
    }
}
