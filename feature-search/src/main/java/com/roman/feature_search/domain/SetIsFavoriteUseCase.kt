package com.roman.feature_search.domain

import com.roman.feature_search.data.VacanciesRepositoryApi
import javax.inject.Inject

internal class SetIsFavoriteUseCase @Inject constructor(
    private val vacanciesRepositoryApi: VacanciesRepositoryApi
):SetIsFavoriteUseCaseApi{
    override suspend fun execute(id:String) {
       vacanciesRepositoryApi.setIsFavorite(id)
    }
}