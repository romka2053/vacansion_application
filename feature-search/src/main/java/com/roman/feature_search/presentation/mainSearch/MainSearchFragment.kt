package com.roman.feature_search.presentation.mainSearch

import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.roman.core.BaseFragment
import com.roman.core.entity.Recommendation
import com.roman.core.entity.VacanciesPreview
import com.roman.core_ui.CustomItemDecorationHorizontal
import com.roman.core.LoadingState
import com.roman.core_ui.removeAllDecoration
import com.roman.feature_search.R
import com.roman.feature_search.databinding.FragmentMainSearchBinding
import com.roman.core_ui.adapter.JobCardMyAdapter
import com.roman.feature_search.SetNavigateApi
import com.roman.feature_search.presentation.RecommendationsMyAdapter
import com.roman.navigation.Info
import com.roman.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class MainSearchFragment :
    BaseFragment<FragmentMainSearchBinding>(FragmentMainSearchBinding::inflate) {


    private val viewModel: MainSearchViewModel by viewModels()
    private val adapterRecommend = RecommendationsMyAdapter()
    private lateinit var recommendations: List<Recommendation>
    private val adapterVacancies =
        JobCardMyAdapter(MAX_COUNT_VACANCIES, { id -> clickLike(id) }, { id -> clickVacancy(id) })
    override val onViewCreatedFun: () -> Unit = ::setContent


    private fun setContent() {

        val setNav = requireActivity() as SetNavigateApi
        setNav.setNavigate()

        lifecycleScope.launch {

            viewModel.uiStateVacancy.collect { uiStateVacancy ->
                when (uiStateVacancy.loadingState) {
                    LoadingState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    LoadingState.Loaded -> {
                        binding.progressBar.visibility = View.GONE
                        setData()

                        uiStateVacancy.vacanciesPreview?.let {
                            setAdapter(it)
                            setButton(it)
                        }

                    }

                    is LoadingState.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }

            }


        }

    }

    private suspend fun setData() {

        recommendations = viewModel.getRecommendation()
    }

    private fun setAdapter(vacancies: List<VacanciesPreview>) {
        adapterRecommend.setData(recommendations)
        adapterVacancies.setData(vacancies)
        binding.recommendationAdapter.adapter = adapterRecommend
        binding.recommendationAdapter.removeAllDecoration()
        binding.recommendationAdapter.addItemDecoration(
            CustomItemDecorationHorizontal(
                requireContext(), 16, 8, 16
            )
        )

        binding.vacanciesAdapter.adapter = adapterVacancies


    }

    private fun setButton(vacancies: List<VacanciesPreview>) {
        binding.buttonMoreVacancies.visibility = if (vacancies.size > MAX_COUNT_VACANCIES) {
            View.VISIBLE
        } else {
            View.GONE
        }
        val text = getString(R.string.more) + " " + resources.getQuantityString(
            com.roman.core_ui.R.plurals.vacanciesCount,
            vacancies.size,
            vacancies.size
        )
        binding.buttonMoreVacancies.text = text
        binding.buttonMoreVacancies.setOnClickListener {
            navigate(R.id.action_mainSearchFragment_to_globalSearchFragment)
        }
    }

    private fun clickVacancy(id: String) {
        navigate(com.roman.navigation.R.id.vacancy_navigation, data = Info(id))
    }

    private fun clickLike(id: String) {
        lifecycleScope.launch {
            viewModel.setIsFavorite(id)
        }
    }

    companion object {
        const val MAX_COUNT_VACANCIES = 3
    }
}