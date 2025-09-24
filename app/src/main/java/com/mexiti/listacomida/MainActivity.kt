package com.mexiti.listacomida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mexiti.listacomida.data.DataSource
import com.mexiti.listacomida.model.Platillo
import com.mexiti.listacomida.ui.theme.ListaComidaTheme
import com.mexiti.listacomida.ui.theme.md_theme_dark_onSecondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaComidaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MenuApp()
                }
            }
        }
    }
}

@Composable
fun MenuApp(){
    MenuCardList(
        platilloList = DataSource().LoadPlatillos(),
    )
}

@Composable
fun MenuCardList( platilloList:List<Platillo>, modifier: Modifier = Modifier ){
    Scaffold(
        topBar = { MenuTopAppBar() }
    ) { paddingValues ->

        // 🔹 NUEVO: ordenar por el orden solicitado
        val orden = listOf("Desayuno","Hamburguesa","Pizza","Postre","Pozole","Tacos")
        val ctx = LocalContext.current
        val ordered = remember(platilloList) {
            platilloList.sortedBy { p ->
                val nombre = ctx.getString(p.stringResourceId)
                val idx = orden.indexOf(nombre)
                if (idx == -1) Int.MAX_VALUE else idx
            }
        }

        LazyColumn(contentPadding = paddingValues){
            items(ordered){ platillo ->
                MenuCard(
                    platillo = platillo,
                    modifier= Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun MenuCard(platillo: Platillo, modifier: Modifier = Modifier) {
    val nombre = stringResource(id = platillo.stringResourceId)

    // Precio y descuento según el ID del recurso:
    val (precio, descuento) = when (platillo.stringResourceId) {
        R.string.desayuno     -> 85.00 to 10
        R.string.hamburger  -> 120.00 to 25
        R.string.pizza        -> 150.00 to 20
        R.string.postre       -> 60.00 to 15
        R.string.pozole       -> 110.00 to 30
        R.string.tacos        -> 90.00 to 18
        else                  -> 99.00 to 10
    }

    Card(modifier = modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = platillo.drawableResourceId),
                contentDescription = nombre,
                modifier = Modifier
                    .size(180.dp)
                    .padding(start = 10.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "MX $${"%.2f".format(precio)}",
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Ahorra hasta el $descuento%",
                    color = md_theme_dark_onSecondary,
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(
                        id = /* AQUI poner logo importado */ R.drawable.logo
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(56.dp) // 🔹 MÁS GRANDE
                )
                Text(
                    text = "CU Restaurant", // 🔹 NUEVO TÍTULO
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        modifier = modifier
    )
}


