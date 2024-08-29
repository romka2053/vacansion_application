package com.roman.feature_search.domain


import com.roman.core.entity.VacanciesPreview
import kotlinx.coroutines.flow.Flow

internal interface GetVacanciesUseCaseApi {
    suspend fun execute(): Flow<List<VacanciesPreview>?>
}