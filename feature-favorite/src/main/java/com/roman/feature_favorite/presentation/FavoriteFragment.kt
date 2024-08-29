package com.roman.feature_favorite.presentation


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.roman.core.BaseFragment
import com.roman.core.LoadingState
import com.roman.feature_favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val adapter = FavoriteAdapter({ id -> clickLike(id) }, { id -> clickRespond(id) })
    private val viewModel: FavoriteViewModel by viewModels()
    override val onViewCreatedFun: () -> Unit = ::setContent


    private fun setContent() {

        binding.isFavoriteText.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.favoriteList.adapter = adapter
        lifecycleScope.launch {
            viewModel.uiStateVacancy.collect {
                Log.d("1111", it.loadingState.toString())
                when (it.loadingState) {
                    LoadingState.Loading -> {}
                    LoadingState.Loaded -> {
                        val text = resources.getQuantityString(
                            com.roman.core_ui.R.plurals.vacanciesCount,
                            it.vacanciesPreview?.size ?: 0,
                            it.vacanciesPreview?.size ?: 0
                        )
                        binding.countVacancies.text = text
                        it.vacanciesPreview?.let {

                            adapter.submitList(it)
                        }

                    }

                    is LoadingState.Error -> {}
                }
            }
        }

    }

    private fun clickLike(id: String) {
        lifecycleScope.launch {
            viewModel.setIsFavorite(id)
        }

    }

    private fun clickRespond(id: String) {

    }
}


