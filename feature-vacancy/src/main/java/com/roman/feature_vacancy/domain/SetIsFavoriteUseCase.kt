package com.roman.feature_vacancy.domain

import com.roman.feature_vacancy.data.VacanciesRepositoryApi
import javax.inject.Inject

internal class SetIsFavoriteUseCase @Inject constructor(
    private val vacanciesRepositoryApi: VacanciesRepositoryApi
):SetIsFavoriteUseCaseApi{
    override suspend fun execute(id:String) {
       vacanciesRepositoryApi.setIsFavorite(id)
    }
}