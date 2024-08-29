package com.roman.core_db_room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.roman.core_db_room.dao.RecommendationDao
import com.roman.core_db_room.dao.VacanciesDao
import com.roman.core_db_room.entity.RecommendationRoom
import com.roman.core_db_room.entity.VacanciesRoom

@Database(
    entities = [
        VacanciesRoom::class,
        RecommendationRoom::class
    ], version = 1
)
@TypeConverters(TypeConvertersQuestions::class)
abstract class DataBase : RoomDatabase() {
    abstract fun vacanciesDao(): VacanciesDao
    abstract fun recommendationDao(): RecommendationDao
}