package com.roman.core_db_room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.roman.core_db_room.entity.RecommendationRoom

@Dao
interface RecommendationDao {
    @Query("SELECT * FROM recommendation")
    suspend fun getAllRecommendations(): List<RecommendationRoom>

    @Insert(entity = RecommendationRoom::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendations(recommendations: List<RecommendationRoom>)
}