package com.roman.core_db_room

import android.util.Log
import com.roman.core.entity.Recommendation
import com.roman.core.entity.Vacancy
import com.roman.core_db_room.dao.RecommendationDao
import com.roman.core_db_room.dao.VacanciesDao
import com.roman.core_db_room.entity.RecommendationRoom
import com.roman.core_db_room.entity.VacanciesRoom
import com.roman.core_local_db_api.LocalDbApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDb @Inject constructor(
    private val vacanciesDao: VacanciesDao,
    private val recommendationDao: RecommendationDao
) : LocalDbApi {
    override fun getAllFlowFavoriteVacancies(): Flow<List<Vacancy>> {
        return vacanciesDao.getAllFlowFavoriteVacancies()
            .map { list -> List(list.size) { list[it].mapToVacancy() } }

    }

    override fun getAllFlowVacancies(): Flow<List<Vacancy>> {
        return vacanciesDao.getAllFlowVacancies()
            .map { list -> List(list.size) { list[it].mapToVacancy() } }
    }

    override suspend fun isNotEmptyLocalBd(): Boolean {
        return vacanciesDao.getOneVacancy()?.let { true } ?: let { false }
    }

    override suspend fun insertVacancy(vacancy: Vacancy) {
        vacanciesDao.insertVacancy(VacanciesRoom(vacancy))
    }

    override fun getVacancyFlowById(id: String): Flow<Vacancy?> {
        return vacanciesDao.getVacancyFlowById(id).map { it?.mapToVacancy() }
    }

    override suspend fun getVacancyById(id: String): Vacancy? {
        return vacanciesDao.getVacancyById(id)?.mapToVacancy()
    }

    override suspend fun setIsFavorite(id: String) {
        vacanciesDao.setIsFavorite(id)
    }

    override suspend fun getAllRecommendations(): List<Recommendation> {
        return recommendationDao.getAllRecommendations().map { it.mapToRecommendation() }
    }

    override suspend fun insertRecommendations(recommendations: List<Recommendation>) {
        recommendationDao.insertRecommendations(recommendations.map { RecommendationRoom(it) })
    }

    override suspend fun insertVacancy(vacancies: List<Vacancy>) {
        vacanciesDao.insertVacancy(vacancies.map { VacanciesRoom(it) })
    }
}