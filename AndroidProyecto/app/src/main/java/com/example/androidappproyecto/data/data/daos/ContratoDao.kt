package com.example.androidappproyecto.data.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidappproyecto.data.data.modelos.Contrato
import kotlinx.coroutines.flow.Flow

@Dao
interface ContratoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContrato(contrato: Contrato): Long

    @Query("SELECT * FROM contratos")
    fun getAllContratos(): Flow<List<Contrato>>

    @Update
    suspend fun updateContrato(contrato: Contrato)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contratos: List<Contrato>) : List<Long>

    @Delete
    suspend fun deleteContrato(contrato: Contrato)

    // Devuelve la lista como suspend function (para inicialización)
    @Query("SELECT * FROM contratos")
    suspend fun getAllContratosOnce(): List<Contrato>
}