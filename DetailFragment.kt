package com.example.ppm_lab06_pokeapi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ppm_lab06_pokeapi.network.Pokemon
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun Detail(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Front",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.paddingFromBaseline(top = 50.dp).offset(x = 20.dp)
            )

            Text(
                text = "Back",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.paddingFromBaseline(top = 50.dp).offset(x = (265).dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 16.dp)) {
            Spacer(modifier = Modifier.width(10.dp))


            GlideImage(
                imageModel = pokemon.imageUrlFront,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )

            Spacer(modifier = Modifier.width(150.dp))

            GlideImage(
                imageModel = pokemon.imageUrlBack,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Front Shiny",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.paddingFromBaseline(top = 100.dp).offset(x = 20.dp)
            )

            Text(
                text = "Back Shiny",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.paddingFromBaseline(top = 100.dp).offset(x = (200).dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 16.dp)) {
            Spacer(modifier = Modifier.width(10.dp))

            GlideImage(
                imageModel = pokemon.imageUrlShinnyFront,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )

            Spacer(modifier = Modifier.width(150.dp))

            GlideImage(
                imageModel = pokemon.imageUrlShinyBack,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )
        }

    }
}