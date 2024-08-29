package com.roman.feature_favorite.di


import com.roman.core_local_db_api.LocalDbApi
import com.roman.core_network_api.DataNetworkApi
import com.roman.feature_favorite.data.VacanciesRepository
import com.roman.feature_favorite.data.VacanciesRepositoryApi
import com.roman.feature_favorite.domain.GetVacanciesUseCase
import com.roman.feature_favorite.domain.GetVacanciesUseCaseApi
import com.roman.feature_favorite.domain.SetIsFavoriteUseCaseApi
import com.roman.feature_favorite.domain.SetIsFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object FeatureDi {

    @Provides
    fun getVacanciesFavoriteRepository(
        dataNetworkApi: DataNetworkApi,
        localDbApi: LocalDbApi
    ): VacanciesRepositoryApi =
        VacanciesRepository(localDbApi, dataNetworkApi)

    @Provides
    fun getVacanciesFavoriteUseCaseApi(repositoryApi: VacanciesRepositoryApi): GetVacanciesUseCaseApi =
        GetVacanciesUseCase(repositoryApi)

    @Provides
    fun setIsFavoriteFavoriteUseCaseApi(repositoryApi: VacanciesRepositoryApi): SetIsFavoriteUseCaseApi =
        SetIsFavoriteUseCase(repositoryApi)
}