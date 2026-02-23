package com.example.androidappproyecto.data.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidappproyecto.data.data.modelos.Solicitud
import kotlinx.coroutines.flow.Flow

@Dao
interface SolicitudDao {

    @Query("SELECT * FROM solicitudes")
    fun getAllSolicitudes(): Flow<List<Solicitud>>

    @Insert
    suspend fun insertSolicitud(solicitud: Solicitud)

    @Update
    suspend fun updateSolicitud(solicitud: Solicitud)

    @Delete
    suspend fun deleteSolicitud(solicitud: Solicitud)

}