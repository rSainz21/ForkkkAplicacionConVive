package com.example.androidappproyecto.database


import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidappproyecto.data.data.modelos.Contrato
import com.example.androidappproyecto.data.data.modelos.Gasto
import com.example.androidappproyecto.data.data.modelos.Inquilino
import com.example.androidappproyecto.data.data.modelos.Oferta
import com.example.androidappproyecto.data.data.modelos.Piso
import com.example.androidappproyecto.data.data.modelos.Propietario
import com.example.androidappproyecto.data.data.modelos.Solicitud
import com.example.androidappproyecto.data.data.modelos.Tarea
import com.example.androidappproyecto.data.data.modelos.Usuario
import kotlin.jvm.java

@Database(
    entities = [
        Contrato::class,
        Gasto::class,
        Inquilino::class,
        Oferta::class,
        Piso::class,
        Solicitud::class,
        Tarea::class,
        Propietario::class,
        Usuario::class,
    ],
    version = 5,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
//    abstract fun rutaDao(): RutaDao
//    abstract fun usuarioDao(): UsuarioDao
//    abstract fun puntoDao(): PuntoDao
//    abstract fun puntosPeligroDao(): PuntospeligroDao
//    abstract fun puntosInteresDao(): PuntosinteresDao
//    abstract fun trackpointDao(): TrackpointDao
//    abstract fun resenaDao(): ResenaDao
//    abstract fun valoracionTecnicaDao(): ValoracionestecnicasDao
//    abstract fun waypointDao(): WaypointDao
//    abstract fun valoracionesDao(): ValoracionesDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mydb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

