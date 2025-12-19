package com.example.androidappproyecto.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.androidappproyecto.pantallas.PantallaBuscar
import com.example.androidappproyecto.pantallas.PantallaHome
import com.example.androidappproyecto.pantallas.PantallaMisPisos
import com.example.androidappproyecto.pantallas.PantallaPerfil
import com.example.androidappproyecto.pantallas.PantallaPremium
import com.example.androidappproyecto.pantallas.Rutas

@Composable
fun AppConviveNavigation(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Rutas.Home.name)
    {
        composable(Rutas.Home.name){ PantallaHome() }
        composable(Rutas.MisPisos.name) { PantallaMisPisos() }
        composable(Rutas.Buscar.name) { PantallaBuscar() }
        composable(Rutas.Perfil.name) { PantallaPerfil() }
        composable(Rutas.Premium.name) { PantallaPremium() }
    }

}



