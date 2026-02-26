package com.example.androidappproyecto.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.androidappproyecto.data.data.modelos.Usuario
import com.example.androidappproyecto.data.data.repositorios.InquilinoPropietarioRepositorio
import com.example.androidappproyecto.data.data.viewmodels.InquilinoPropietarioViewModel
import com.example.androidappproyecto.data.data.viewmodels.PisoViewModel
import com.example.androidappproyecto.database.ApiCliente
import com.example.androidappproyecto.database.AppDatabase
import com.example.androidappproyecto.pantallas.PantallaBuscar
import com.example.androidappproyecto.pantallas.PantallaDetallePiso
import com.example.androidappproyecto.pantallas.PantallaHome
import com.example.androidappproyecto.pantallas.PantallaMisPisos
import com.example.androidappproyecto.pantallas.PantallaPerfil
import com.example.androidappproyecto.pantallas.PantallaChat


@Composable
fun AppConviveNavigation(navController: NavHostController, modifier: Modifier,
                         pisoViewModel: PisoViewModel
) {

    val currentUser = Usuario(
        id = 1,
        nombre_usuario = "miguelq",
        nombre_real = "miguel angel",
        fecha_nacimiento = "2005-12-12",
        password = "1234",
        email = "miguel@gmail.com"
    )

    NavHost(navController = navController, modifier = modifier, startDestination = Rutas.Home.name) {

        composable(Rutas.Home.name) { PantallaHome(pisoViewModel) }
        composable(Rutas.Buscar.name) { PantallaBuscar() }

        // Chat directo
        composable(
            route = Rutas.Chat.name + "/{inqId}/{propId}",
            arguments = listOf(
                navArgument("inqId") { type = NavType.IntType },
                navArgument("propId") { type = NavType.IntType }
            )
        ) { backStack ->

            val context = LocalContext.current
            val inq = backStack.arguments?.getInt("inqId") ?: 0
            val prop = backStack.arguments?.getInt("propId") ?: 0

            val chatViewModel: InquilinoPropietarioViewModel =
                viewModel(
                    key = "chat_${inq}_${prop}",
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            val database = AppDatabase.getDatabase(context)
                            val apiService = ApiCliente.inquilinoPropietarioApi
                            val repository = InquilinoPropietarioRepositorio(
                                apiService,
                                database.inquilinoPropietarioDao()
                            )
                            @Suppress("UNCHECKED_CAST")
                            return InquilinoPropietarioViewModel(repository) as T
                        }
                    }
                )

            PantallaChat(viewModel = chatViewModel, inqId = inq, propId = prop)
        }

        composable(Rutas.MisPisos.name) { PantallaMisPisos(navController) }
        composable(Rutas.Perfil.name) { PantallaPerfil(user = currentUser) }
        composable(Rutas.DetallePiso.name) { PantallaDetallePiso() }
    }
}


