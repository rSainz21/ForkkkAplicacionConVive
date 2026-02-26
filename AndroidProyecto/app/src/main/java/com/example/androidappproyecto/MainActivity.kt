package com.example.androidappproyecto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidappproyecto.data.data.DTO.PropietarioDTO
import com.example.androidappproyecto.data.data.modelos.Direccion
import com.example.androidappproyecto.data.data.modelos.Piso
import com.example.androidappproyecto.data.data.modelos.Propietario
import com.example.androidappproyecto.data.data.repositorios.PisoRepositorio
import com.example.androidappproyecto.data.data.viewmodels.PisoViewModel
import com.example.androidappproyecto.database.ApiCliente
import com.example.androidappproyecto.database.AppDatabase
import com.example.androidappproyecto.pantallas.ProyectoView
import com.example.androidappproyecto.ui.theme.AndroidAppProyectoTheme
import kotlinx.coroutines.launch

// MainActivity.kt
class MainActivity : ComponentActivity() {

    private lateinit var pisoViewModel: PisoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getDatabase(applicationContext)
        val pisoRepository = PisoRepositorio(
            pisoDao = db.pisoDao(),
            pisoApi = ApiCliente.pisoApi
        )

        // ViewModel Factory
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PisoViewModel(
                    repositorio = pisoRepository,
                    pisoDao = db.pisoDao()
                ) as T
            }
        }

        // DTO para Retrofit (evitar problemas de @Body converter)
        val propietarioDTO = PropietarioDTO(
            nombre_usuario = "propietario1",
            nombre_real = "Juan Perez",
            fecha_nacimiento = "1980-01-01",
            email = "juan@example.com",
            password = "1234"
        )

        // Lanzamos coroutine para insertar el propietario y piso
        lifecycleScope.launch {
            try {

                val response = ApiCliente.propietarioApi.createPropietario(propietarioDTO)
                if (response.isSuccessful) {
                    Log.d("API", "Propietario insertado en MySQL")


                    val propietarioRoom = Propietario(
                        id = response.body()?.id ?: 0,
                        nombre_usuario = propietarioDTO.nombre_usuario,
                        nombre_real = propietarioDTO.nombre_real,
                        fecha_nacimiento = propietarioDTO.fecha_nacimiento,
                        email = propietarioDTO.email,
                        password = propietarioDTO.password
                    )
                    db.propietarioDao().insertPropietario(propietarioRoom)

                    // 3️⃣ Crear piso asociado
                    val piso1 = Piso(
                        id = 0, // Room autoGenerate
                        titulo = "Piso Demo",
                        descripcion = "Piso de prueba con 2 habitaciones",
                        direccion = Direccion("Calle Demo 1", "Madrid", "Madrid"),
                        disponible = true,
                        precio = 120000.0,
                        url_imagen = "https://example.com/piso.jpg",
                        validado = true,
                        propietario = propietarioRoom
                    )
                    db.pisoDao().insertPiso(piso1)

                } else {
                    Log.e("API", "Error al insertar propietario: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Exception al insertar propietario: ${e.message}")
            } finally {
                // Inicializamos el ViewModel y la UI
                pisoViewModel = ViewModelProvider(this@MainActivity, factory)[PisoViewModel::class.java]
                setContent {
                    AndroidAppProyectoTheme {
                        MainScreen(pisoViewModel = pisoViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(pisoViewModel: PisoViewModel) {
    ProyectoView(
        pisoViewModel = pisoViewModel
    )
}
