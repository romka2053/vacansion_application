package com.roman.feature_vacancy.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.core.LoadingState
import com.roman.feature_vacancy.domain.GetVacancyUseCaseApi
import com.roman.feature_vacancy.domain.SetIsFavoriteUseCaseApi
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
internal class VacancyViewModel @Inject constructor(
    private val getVacancyUseCaseApi: GetVacancyUseCaseApi,
    private val setIsFavoriteUseCaseApi: SetIsFavoriteUseCaseApi
) : ViewModel() {
    private var id: String = ""
    private val trigger = MutableSharedFlow<Unit>(replay = 1)
    private var _uiState = MutableStateFlow(VacancyUi(null, LoadingState.Loading)).asStateFlow()
    val uiState get() = _uiState
    init {
        _uiState = trigger.flatMapLatest {
            getVacancyUseCaseApi.execute(id).map {
                VacancyUi(
                    it,
                    if (it?.id ==id) LoadingState.Loaded else LoadingState.Loading
                )
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            VacancyUi(null, LoadingState.Loading)
        )

    }


    fun refresh(id: String) {
        viewModelScope.launch {
            this@VacancyViewModel.id = id
            trigger.emit(Unit)
        }
    }

    suspend fun setIsFavorite(id: String) {
        setIsFavoriteUseCaseApi.execute(id)
    }


}

