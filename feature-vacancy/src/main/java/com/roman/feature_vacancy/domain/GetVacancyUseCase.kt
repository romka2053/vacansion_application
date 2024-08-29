package com.roman.feature_vacancy.domain

import com.roman.feature_vacancy.data.VacanciesRepositoryApi
import com.roman.core.entity.Vacancy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetVacancyUseCase @Inject constructor(
    private val vacanciesRepositoryApi: VacanciesRepositoryApi
):GetVacancyUseCaseApi{
    override suspend fun execute(id:String): Flow<Vacancy?> {
      return vacanciesRepositoryApi.getVacancies(id)
    }
}