package com.roman.feature_search.domain

import com.roman.core.entity.Recommendation


internal interface GetRecommendationUseCaseApi {
    suspend fun execute(): List<Recommendation>
}