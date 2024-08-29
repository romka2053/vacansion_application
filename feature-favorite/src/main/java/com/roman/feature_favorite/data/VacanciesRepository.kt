package com.roman.feature_favorite.data


import com.roman.core_local_db_api.LocalDbApi
import com.roman.core.entity.VacanciesPreview
import com.roman.core_network_api.DataNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import android.util.Log

internal class VacanciesRepository @Inject constructor(
    private val localDbApi: LocalDbApi,
    private val dataNetworkApi: DataNetworkApi
) : VacanciesRepositoryApi {

    override suspend fun getVacancies(): Flow<List<VacanciesPreview>> {

        val vacancies = localDbApi.getAllFlowFavoriteVacancies().map { list ->

            if (!localDbApi.isNotEmptyLocalBd()) {
                localDbApi.insertVacancy(
                    dataNetworkApi.getVacancies()
                )
                emptyList()
            } else {
                List(list.size) { VacanciesPreview(list[it]) }
            }
        }
        return vacancies
    }

    override suspend fun setIsFavorite(id: String) {
        localDbApi.setIsFavorite(id)
    }
}