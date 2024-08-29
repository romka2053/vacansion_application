package com.roman.feature_search.data

import com.roman.core_local_db_api.LocalDbApi
import com.roman.core.entity.VacanciesPreview
import com.roman.core.entity.Vacancy
import com.roman.core_network_api.DataNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class VacanciesRepository @Inject constructor(
    private val localDbApi: LocalDbApi,
    private val dataNetworkApi: DataNetworkApi
) : VacanciesRepositoryApi {

    override suspend fun getVacancies(): Flow<List<VacanciesPreview>?> {

        if (!localDbApi.isNotEmptyLocalBd()) {
            localDbApi.insertVacancy(
                dataNetworkApi.getVacancies()
            )
        }

        val vacancies = localDbApi.getAllFlowVacancies().map { list ->
            list?.let {
                List(list.size) { VacanciesPreview(list[it]) }
            }
        }

        return vacancies
    }

    override suspend fun setIsFavorite(id: String) {
        localDbApi.setIsFavorite(id)
    }
}