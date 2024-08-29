package com.roman.feature_search.domain


import com.roman.core.entity.Recommendation
import com.roman.feature_search.data.RecommendationRepositoryApi
import javax.inject.Inject

internal class GetRecommendationUseCase @Inject constructor(
    private val recommendationRepository: RecommendationRepositoryApi
) : GetRecommendationUseCaseApi {
    override suspend fun execute(): List<Recommendation> {
        return recommendationRepository.getRecommendation()
    }
}