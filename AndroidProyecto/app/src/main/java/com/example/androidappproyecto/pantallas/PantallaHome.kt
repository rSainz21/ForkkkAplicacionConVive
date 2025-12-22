package com.example.androidappproyecto.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.androidappproyecto.R
import com.example.androidappproyecto.data.pisos
import com.example.androidappproyecto.modelos.Piso
import java.time.LocalDate

@Preview(showBackground = true)
@Composable
fun PantallaHome(){
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ){
        items(pisos){ piso ->
            PisoSeccion(piso)
        }
    }
}

@Composable
fun PisoSeccion(piso : Piso){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        shape = CardDefaults.shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF800000))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ImagenPisoCard(
                url = piso.urlImagen,
                desc = "Imagen del artista"
            )
            Spacer(modifier = Modifier.width(16.dp))
            InfoArtist(piso, modifier = Modifier.weight(0.75f))

        }
    }
}

@Composable
fun InfoArtist(piso: Piso, modifier: Modifier) {
    Column(
        modifier=modifier
    ) {
        Text(
            text = piso.titulo,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun ImagenPisoCard(url: String?, desc: String){
    GlideImage(
        model = url,
        contentDescription = desc,
        modifier =  Modifier.size(80.dp)
            .clip(CircleShape)
            .background(Color.Gray),
        contentScale = ContentScale.Crop,
        loading = placeholder(R.drawable.loading),
        failure = placeholder(R.drawable.error)
    )
}

fun seleccionDelPiso(): Piso {
    val tituloValido = ""
    return pisos.find { it.titulo != tituloValido } ?: Piso(titulo = " ",
        direccion = " ",
        descripcion = " ",
        propietario = " ",
        urlImagen = " ")
}