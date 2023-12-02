package com.mz_dev.prueba_tecnica.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.mz_dev.prueba_tecnica.data.local.model.LocalCharacter
import com.mz_dev.prueba_tecnica.data.local.model.LocalSerie
import kotlinx.coroutines.flow.Flow

@Database(entities = [LocalCharacter::class, LocalSerie::class], version = 1)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun getDao(): MarvelDao
}

@Dao
interface MarvelDao {

    @Query("SELECT * FROM LocalCharacter")
    fun getCharacters(): Flow<List<LocalCharacter>>

    @Query("SELECT * FROM LocalSerie")
    fun getSeries(): Flow<List<LocalSerie>>

    @Insert
    suspend fun insertAllCharacters(characters: List<LocalCharacter>)

    @Insert
    suspend fun insertAllSeries(series: List<LocalSerie>)

    @Update
    suspend fun updateCharacter(character: LocalCharacter)

    @Query("SELECT COUNT(*) FROM LocalCharacter")
    suspend fun countCharacters(): Int

    @Query("SELECT COUNT(*) FROM LocalSerie")
    suspend fun countSeries(): Int
}