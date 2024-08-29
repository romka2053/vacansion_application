package com.roman.core_local_db_api

import com.roman.core.entity.Recommendation
import com.roman.core.entity.Vacancy
import kotlinx.coroutines.flow.Flow

interface LocalDbApi {
    fun getAllFlowVacancies(): Flow<List<Vacancy>>

    fun getAllFlowFavoriteVacancies(): Flow<List<Vacancy>>

    suspend fun isNotEmptyLocalBd(): Boolean

    suspend fun insertVacancy(vacancy: Vacancy)

    fun getVacancyFlowById(id: String): Flow<Vacancy?>

    suspend fun getVacancyById(id: String): Vacancy?

    suspend fun setIsFavorite(id: String)

    suspend fun insertVacancy(vacancies: List<Vacancy>)

    suspend fun getAllRecommendations():List<Recommendation>

    suspend fun insertRecommendations(recommendations:List<Recommendation>)

}