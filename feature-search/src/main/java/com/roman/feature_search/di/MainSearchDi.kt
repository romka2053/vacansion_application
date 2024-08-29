package com.roman.feature_search.di


import com.roman.core_local_db_api.LocalDbApi
import com.roman.core_network_api.DataNetworkApi
import com.roman.feature_search.data.RecommendationRepository
import com.roman.feature_search.data.RecommendationRepositoryApi
import com.roman.feature_search.data.VacanciesRepository
import com.roman.feature_search.data.VacanciesRepositoryApi
import com.roman.feature_search.domain.GetRecommendationUseCase
import com.roman.feature_search.domain.GetRecommendationUseCaseApi
import com.roman.feature_search.domain.GetVacanciesUseCase
import com.roman.feature_search.domain.GetVacanciesUseCaseApi
import com.roman.feature_search.domain.SetIsFavoriteUseCase
import com.roman.feature_search.domain.SetIsFavoriteUseCaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object MainSearchDi {

    @Provides
    fun getRepository(
        localDbApi: LocalDbApi,
        dataNetworkApi: DataNetworkApi,
    ): RecommendationRepositoryApi =
        RecommendationRepository(dataNetworkApi, localDbApi)

    @Provides
    fun getRecommendationUseCaseApi(repositoryApi: RecommendationRepositoryApi): GetRecommendationUseCaseApi =
        GetRecommendationUseCase(repositoryApi)

    @Provides
    fun getVacanciesRepository(
        dataNetworkApi: DataNetworkApi,
        localDbApi: LocalDbApi
    ): VacanciesRepositoryApi =
        VacanciesRepository(localDbApi, dataNetworkApi)

    @Provides
    fun getVacanciesUseCaseApi(repositoryApi: VacanciesRepositoryApi): GetVacanciesUseCaseApi =
        GetVacanciesUseCase(repositoryApi)

    @Provides
    fun setIsFavoriteUseCaseApi(repositoryApi: VacanciesRepositoryApi): SetIsFavoriteUseCaseApi =
        SetIsFavoriteUseCase(repositoryApi)
}