package com.roman.feature_vacancy.data

import com.roman.core.entity.Vacancy
import kotlinx.coroutines.flow.Flow

internal interface VacanciesRepositoryApi {
   suspend fun getVacancies(id:String):Flow<Vacancy?>
   suspend fun setIsFavorite(id: String)
}