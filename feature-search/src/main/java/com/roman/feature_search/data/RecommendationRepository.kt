package com.roman.feature_search.data

import com.roman.core.entity.Recommendation
import com.roman.core_local_db_api.LocalDbApi
import com.roman.core_network_api.DataNetworkApi
import javax.inject.Inject

internal class RecommendationRepository @Inject constructor(
    private val dataNetworkApi: DataNetworkApi,
    private val localDbApi: LocalDbApi
) :
    RecommendationRepositoryApi {

    override suspend fun getRecommendation(): List<Recommendation> {
        return localDbApi.getAllRecommendations().ifEmpty {
            val networkData = dataNetworkApi.getRecommendations()
            localDbApi.insertRecommendations(networkData)
            return networkData

        }
    }


}