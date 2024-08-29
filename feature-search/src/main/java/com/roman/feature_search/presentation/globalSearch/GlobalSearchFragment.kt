package com.roman.feature_search.presentation.globalSearch


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.roman.core.BaseFragment
import com.roman.core.LoadingState
import com.roman.core.entity.VacanciesPreview
import com.roman.core_ui.adapter.JobCardMyAdapter
import com.roman.feature_search.databinding.FragmentGlobalSearchBinding
import com.roman.navigation.Info
import com.roman.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class GlobalSearchFragment :
    BaseFragment<FragmentGlobalSearchBinding>(FragmentGlobalSearchBinding::inflate) {

    private val viewModel: GlobalSearchViewModel by viewModels()
    private val adapterVacancies =
        JobCardMyAdapter(null, { id -> clickLike(id) }, { id -> clickVacancy(id) })
    override val onViewCreatedFun: () -> Unit = ::setContent


    private fun setContent() {
        lifecycleScope.launch {
            binding.imageBack.setOnClickListener {
                findNavController().popBackStack()
            }
            viewModel.uiStateVacancy.collect { uiStateVacancy ->
                when (uiStateVacancy.loadingState) {
                    LoadingState.Loading -> {}
                    LoadingState.Loaded -> {

                        uiStateVacancy.vacanciesPreview?.let {
                            setText(it)
                            setAdapter(it)
                        }
                    }

                    is LoadingState.Error -> {}
                }
            }
        }
    }

    private fun setText(vacancies: List<VacanciesPreview>) {
        val text = resources.getQuantityString(
            com.roman.core_ui.R.plurals.vacanciesCount,
            vacancies.size,
            vacancies.size
        )
        binding.countVacancies.text = text
    }

    private fun setAdapter(vacancies: List<VacanciesPreview>) {
        adapterVacancies.setData(vacancies)
        binding.vacanciesAdapter.adapter = adapterVacancies
    }

    private fun clickLike(id: String) {
        lifecycleScope.launch {
            viewModel.setIsFavorite(id)
        }
    }

    private fun clickVacancy(id: String) {
        navigate(com.roman.navigation.R.id.vacancy_navigation, data = Info(id))
    }

}