package com.roman.feature_favorite.data

import com.roman.core.entity.VacanciesPreview
import kotlinx.coroutines.flow.Flow

internal interface VacanciesRepositoryApi {
    suspend fun getVacancies(): Flow<List<VacanciesPreview>>
    suspend fun setIsFavorite(id: String)
}