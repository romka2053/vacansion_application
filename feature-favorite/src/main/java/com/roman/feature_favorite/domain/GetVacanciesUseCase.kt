package com.roman.feature_favorite.domain

import com.roman.core.entity.VacanciesPreview
import com.roman.feature_favorite.data.VacanciesRepositoryApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetVacanciesUseCase @Inject constructor(
    private val vacanciesRepositoryApi: VacanciesRepositoryApi
) : GetVacanciesUseCaseApi {
    override suspend fun execute(): Flow<List<VacanciesPreview>?> {
        return vacanciesRepositoryApi.getVacancies()
    }
}