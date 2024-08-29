package com.roman.core_db_room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.roman.core_db_room.entity.VacanciesRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface VacanciesDao {
    @Transaction
    @Query("SELECT * FROM vacancies")
    fun getAllFlowVacancies(): Flow<List<VacanciesRoom>>

    @Transaction
    @Query("SELECT * FROM vacancies WHERE isFavorite='1' ")
    fun getAllFlowFavoriteVacancies(): Flow<List<VacanciesRoom>>

    @Query("SELECT * FROM vacancies")
    suspend fun getAllVacancies(): List<VacanciesRoom>

    @Query("SELECT * FROM vacancies limit 1")
    suspend fun getOneVacancy(): VacanciesRoom?

    @Transaction
    @Query("SELECT * FROM vacancies where id = :id")
    fun getVacancyFlowById(id: String): Flow<VacanciesRoom?>

    @Query("UPDATE vacancies SET isFavorite=NOT isFavorite where id = :id")
    suspend fun setIsFavorite(id: String)

    @Query("SELECT * FROM vacancies where id = :id")
    suspend fun getVacancyById(id: String): VacanciesRoom?


    @Insert(entity = VacanciesRoom::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacanciesRoom)

    @Insert(entity = VacanciesRoom::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancies: List<VacanciesRoom>)

}