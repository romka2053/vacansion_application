package com.roman.feature_search.presentation.mainSearch


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.core.LoadingState
import com.roman.feature_search.domain.GetRecommendationUseCaseApi
import com.roman.feature_search.domain.GetVacanciesUseCaseApi
import com.roman.feature_search.domain.SetIsFavoriteUseCaseApi
import com.roman.core.entity.VacancyUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainSearchViewModel @Inject constructor(
    private val useCaseRecommendApi: GetRecommendationUseCaseApi,
    private val useCaseVacanciesApi: GetVacanciesUseCaseApi,
    private val setIsFavoriteUseCaseApi: SetIsFavoriteUseCaseApi
) : ViewModel() {
    private var _uiStateVacancy =
        MutableStateFlow(VacancyUi(null, LoadingState.Loading)).asStateFlow()
    val uiStateVacancy get() = _uiStateVacancy
    private val trigger = MutableSharedFlow<Unit>(replay = 1)

    suspend fun getRecommendation() = useCaseRecommendApi.execute()

    init {


        _uiStateVacancy = trigger.flatMapLatest { _ ->

            useCaseVacanciesApi.execute().map {
                VacancyUi(
                    it,
                    if (it?.isNotEmpty() == true) LoadingState.Loaded else LoadingState.Loading
                )
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            VacancyUi(null, LoadingState.Loading)
        )
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            trigger.emit(Unit)
        }
    }

    suspend fun setIsFavorite(id: String) {
        setIsFavoriteUseCaseApi.execute(id)
    }
}