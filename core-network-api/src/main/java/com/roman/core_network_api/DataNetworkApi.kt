package com.roman.core_network_api

import com.roman.core.entity.Recommendation
import com.roman.core.entity.Vacancy


interface DataNetworkApi {
    suspend fun getRecommendations(): List<Recommendation>
    suspend fun getVacancies():  List<Vacancy>
    suspend fun getGetVacancy(id:String):  Vacancy
}