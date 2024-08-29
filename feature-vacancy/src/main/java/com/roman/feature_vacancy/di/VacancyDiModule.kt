package com.roman.feature_vacancy.di


import com.roman.core_local_db_api.LocalDbApi
import com.roman.core_network_api.DataNetworkApi
import com.roman.feature_vacancy.data.VacanciesRepository
import com.roman.feature_vacancy.data.VacanciesRepositoryApi
import com.roman.feature_vacancy.domain.GetVacancyUseCase
import com.roman.feature_vacancy.domain.GetVacancyUseCaseApi
import com.roman.feature_vacancy.domain.SetIsFavoriteUseCase
import com.roman.feature_vacancy.domain.SetIsFavoriteUseCaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object VacancyDiModule {



    @Provides
    fun getRepository(dataNetworkApi: DataNetworkApi,
                      localDbApi: LocalDbApi

    ): VacanciesRepositoryApi =
        VacanciesRepository(dataNetworkApi,localDbApi)

    @Provides
    fun getVacancyUseCase(repository: VacanciesRepositoryApi):GetVacancyUseCaseApi=
        GetVacancyUseCase(repository)

    @Provides
    fun setIsFavoriteUseCase(repository: VacanciesRepositoryApi):SetIsFavoriteUseCaseApi=
        SetIsFavoriteUseCase(repository)

}