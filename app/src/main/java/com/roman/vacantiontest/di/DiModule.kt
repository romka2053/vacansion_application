package com.roman.vacantiontest.di

import com.roman.core_db_room.DataBase
import com.roman.core_db_room.LocalDb
import com.roman.core_db_room.dao.RecommendationDao
import com.roman.core_db_room.dao.VacanciesDao
import com.roman.core_local_db_api.LocalDbApi
import com.roman.feature_response.DialogRespond
import com.roman.feature_response_api.DialogRespondApi
import com.roman.feature_search.SetNavigateApi
import com.roman.vacantiontest.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiModule {


    @Provides
    fun getDialog(): DialogRespondApi = DialogRespond()

    @Provides
    fun provideLocalDb(vacanciesDao: VacanciesDao, recommendationDao: RecommendationDao): LocalDbApi =
        LocalDb(vacanciesDao,recommendationDao)





}