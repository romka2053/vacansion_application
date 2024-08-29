package com.roman.feature_search.data

import com.roman.core.entity.Recommendation


internal interface RecommendationRepositoryApi {
    suspend fun getRecommendation(): List<Recommendation>

}