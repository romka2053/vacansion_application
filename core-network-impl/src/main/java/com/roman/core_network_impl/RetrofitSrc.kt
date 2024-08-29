package com.roman.core_network_impl

import com.roman.core_network_impl.dto.RecommendationsDto
import com.roman.core_network_impl.dto.VacanciesDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class RetrofitSrc(baseUrl: String) {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    val recommendations: GetRecommendations =
        retrofit.create(GetRecommendations::class.java)
    val vacancies: GetVacancies =
        retrofit.create(GetVacancies::class.java)


    interface GetRecommendations {
        @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download ")
        suspend fun getRecommendations(): RecommendationsDto
    }

    interface GetVacancies {
        @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download ")
        suspend fun getVacancies(): VacanciesDto
    }


}
