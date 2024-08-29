package com.roman.feature_vacancy.data

import com.roman.core_local_db_api.LocalDbApi
import com.roman.core.entity.Vacancy
import com.roman.core_network_api.DataNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class VacanciesRepository @Inject constructor(
    private  val dataNetworkApi: DataNetworkApi,
    private val dataLocalApi: LocalDbApi
) : VacanciesRepositoryApi {


    override suspend fun getVacancies(id:String): Flow<Vacancy?> {
        val vacancyFromLocalBd=dataLocalApi.getVacancyById(id)
        if (vacancyFromLocalBd==null){
            addToDb(id)
        }
        return dataLocalApi.getVacancyFlowById(id)

    }
    private suspend fun addToDb(id: String){

        val vacancy=  dataNetworkApi.getGetVacancy(id)
        dataLocalApi.insertVacancy(
            vacancy
        )
    }

    override suspend fun setIsFavorite(id: String) {
            dataLocalApi.setIsFavorite(id)
    }
}