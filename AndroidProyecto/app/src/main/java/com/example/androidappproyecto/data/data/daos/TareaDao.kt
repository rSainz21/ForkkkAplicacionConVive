package com.example.androidappproyecto.data.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidappproyecto.data.data.modelos.Tarea
import kotlinx.coroutines.flow.Flow

@Dao
interface TareaDao {

    @Query("SELECT * FROM tareas")
    fun getAllTareas(): Flow<List<Tarea>>

    @Insert
    suspend fun insertTarea(tarea: Tarea)

    @Update
    suspend fun updateTarea(tarea: Tarea)

    @Delete
    suspend fun deleteTarea(tarea: Tarea)

}