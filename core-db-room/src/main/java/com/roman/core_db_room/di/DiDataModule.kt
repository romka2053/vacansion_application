package com.roman.core_db_room.di

import android.content.Context
import androidx.room.Room
import com.roman.core_db_room.DataBase
import com.roman.core_db_room.LocalDb
import com.roman.core_db_room.dao.RecommendationDao
import com.roman.core_db_room.dao.VacanciesDao
import com.roman.core_local_db_api.LocalDbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiDataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): DataBase {
        return Room.inMemoryDatabaseBuilder(
            appContext,
            DataBase::class.java,
        ).build()
    }

    @Provides
    fun getVacancyDao(db: DataBase): VacanciesDao {
        return db.vacanciesDao()
    }

    @Provides
    fun getRecommendationDao(db: DataBase): RecommendationDao {
        return db.recommendationDao()
    }


}