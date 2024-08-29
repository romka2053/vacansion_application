package com.roman.feature_favorite.domain

import com.roman.feature_favorite.data.VacanciesRepositoryApi
import javax.inject.Inject

internal class SetIsFavoriteUseCase @Inject constructor(
    private val vacanciesRepositoryApi: VacanciesRepositoryApi
) : SetIsFavoriteUseCaseApi {
    override suspend fun execute(id: String) {
        vacanciesRepositoryApi.setIsFavorite(id)
    }
}