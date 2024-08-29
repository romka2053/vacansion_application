package com.roman.feature_vacancy.domain

import com.roman.core.entity.Vacancy
import kotlinx.coroutines.flow.Flow

internal interface GetVacancyUseCaseApi {
   suspend fun execute(id:String): Flow<Vacancy?>
}