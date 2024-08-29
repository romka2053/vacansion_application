package com.roman.feature_vacancy.presentation

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.roman.core.BaseFragment
import com.roman.core.LoadingState
import com.roman.feature_response_api.DialogRespondApi
import com.roman.feature_vacancy.R
import com.roman.feature_vacancy.databinding.FragmentVacancyBinding
import com.roman.feature_vacancy.databinding.QuestionsItemBinding
import com.roman.core.entity.Vacancy
import com.roman.navigation.Info
import com.roman.navigation.navigationData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
internal class VacancyFragment :
    BaseFragment<FragmentVacancyBinding>(FragmentVacancyBinding::inflate) {

    @Inject
    lateinit var dialog: DialogRespondApi
    private val viewModel: VacancyViewModel by viewModels()
    override val onViewCreatedFun: () -> Unit = ::setContent
    private var id: String = ""

    override val onCreateFun: () -> Unit
        get() = {
            id = (navigationData as? Info)?.valString ?: ""

        }


    private fun setContent() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.refresh(id)

        lifecycleScope.launch {

            viewModel.uiState.collect {
                when (it.loadingState) {
                    LoadingState.Loading -> {
                        binding.root.visibility = View.INVISIBLE
                    }

                    LoadingState.Loaded -> {
                        binding.root.visibility = View.VISIBLE

                        it.vacancy?.let {
                            setTexts(it)
                        }
                    }

                    is LoadingState.Error -> {
                        Log.d("1111", it.toString())
                    }
                }
            }

        }
    }


    private fun setTexts(vacancy: Vacancy) {

        binding.nameVacancy.text = vacancy.title
        binding.salary.text = vacancy.salary
        val requiredExperienceText =
            getString(R.string.required_experience) + ": " + vacancy.experience
        binding.experience.text = requiredExperienceText
        binding.schedules.text = vacancy.schedules
        setPeoplesCount(vacancy)
        binding.company.text = vacancy.company
        binding.address.text = vacancy.address
        binding.description.text = vacancy.description
        binding.responsibilities.text = vacancy.responsibilities
        setList(vacancy)

        binding.buttonRespond.setOnClickListener {
            dialog.show(parentFragmentManager, vacancy.title)
        }
        binding.buttonLike.setOnClickListener {
            lifecycleScope.launch {
                viewModel.setIsFavorite(vacancy.id)
            }
        }
        val drawable = if (vacancy.isFavorite) {
            com.roman.core_ui.R.drawable.like_active
        } else {
            com.roman.core_ui.R.drawable.like_inactive
        }
        binding.buttonLike.setImageDrawable(resources.getDrawable(drawable))

    }

    private fun setPeoplesCount(vacancy: Vacancy) {
        val listPeopleShow = mutableListOf<Pair<String, Int>>()

        addCountPeopleToList(
            vacancy.appliedNumber,
            R.plurals.peopleCountApplied, com.roman.core_ui.R.drawable.profile_small_green
        )?.let {
            listPeopleShow.add(it)
        }

        addCountPeopleToList(
            vacancy.lookingNumber,
            R.plurals.peopleCountView, com.roman.core_ui.R.drawable.view_small_green
        )?.let {
            listPeopleShow.add(it)
        }
        when {
            listPeopleShow.isEmpty() -> {
                binding.peopleShow1.visibility = View.GONE
                binding.peopleShow2.visibility = View.GONE
            }

            listPeopleShow.size == 1 -> {
                peopleShowVisible(
                    binding.peopleShow1, binding.peopleShowText1,
                    binding.peopleShowImage1, listPeopleShow[0]
                )
            }

            else -> {
                peopleShowVisible(
                    binding.peopleShow1, binding.peopleShowText1,
                    binding.peopleShowImage1, listPeopleShow.first()
                )
                peopleShowVisible(
                    binding.peopleShow2, binding.peopleShowText2,
                    binding.peopleShowImage2, listPeopleShow.last()
                )
            }
        }

    }

    private fun addCountPeopleToList(
        count: Int,
        idPlurals: Int,
        idDraw: Int
    ): Pair<String, Int>? {
        if (count != 0) {
            val text = resources.getQuantityString(
                idPlurals, count,
                count
            )
            return Pair(text, idDraw)
        }
        return null
    }

    private fun peopleShowVisible(
        frameLayout: FrameLayout,
        textView: TextView,
        imageView: ImageView,
        data: Pair<String, Int>
    ) {
        frameLayout.visibility = View.VISIBLE
        textView.text = data.first
        AppCompatResources.getDrawable(requireContext(), data.second)
            ?.let {
                imageView.setImageDrawable(it)
            }
    }

    private fun setList(vacancy: Vacancy) {
        binding.linerForButton.removeAllViews()
        vacancy.questions.forEach {
            val textView =
                QuestionsItemBinding.inflate(layoutInflater, binding.linerLayout, false).root
            textView.text = it
            binding.linerForButton.addView(textView)
        }
    }


}