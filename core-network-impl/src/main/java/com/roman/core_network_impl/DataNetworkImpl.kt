package com.roman.core_network_impl

import com.roman.core.entity.Recommendation
import com.roman.core.entity.Vacancy
import com.roman.core_network_api.DataNetworkApi
import javax.inject.Inject

class DataNetworkImpl @Inject constructor(private val retrofitSrc: RetrofitSrc) : DataNetworkApi {
    override suspend fun getGetVacancy(id: String): Vacancy {
        val vacanciesList = retrofitSrc.vacancies.getVacancies().vacancies
        vacanciesList.forEach {
            if (it.id == id) return it.mapToVacancy()
        }
        throw Exception("There is no this element in network")
    }

    override suspend fun getRecommendations(): List<Recommendation> {
        return retrofitSrc.recommendations.getRecommendations().mapToListRecommendation()
    }

    override suspend fun getVacancies(): List<Vacancy> {
        return retrofitSrc.vacancies.getVacancies().mapToListVacancies()
    }
}