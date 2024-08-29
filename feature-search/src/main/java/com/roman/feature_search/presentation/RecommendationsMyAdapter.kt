package com.roman.feature_search.presentation

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.roman.core.BaseMyAdapter
import com.roman.core.BaseViewHolder
import com.roman.core.entity.Recommendation
import com.roman.feature_search.databinding.ItemRecommendationsBinding

internal class RecommendationsMyAdapter :
    BaseMyAdapter<ItemRecommendationsBinding, List<Recommendation>>(ItemRecommendationsBinding::inflate) {

        private val mapIcons= mapOf(
            Pair("near_vacancies", com.roman.core_ui.R.drawable.near_vacancies),
            Pair("level_up_resume",com.roman.core_ui.R.drawable.level_up_resume),
            Pair("temporary_job",com.roman.core_ui.R.drawable.temporary_job)
        )
    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemRecommendationsBinding>,
        position: Int
    ) {


            with(holder){
                binding.textRecommendation.text=data[position].title.trim()
               val idRecommend= mapIcons[data[position].id]
                val image=binding.imageRecommend
                Log.d("1111",idRecommend.toString())
                idRecommend?.let {
                    image.visibility=View.VISIBLE
                  Glide.with(image)
                      .load(it)
                      .centerCrop()
                      .into(image)
                }//?:let{image.visibility=View.GONE}

                data[position].button?.let {
                    binding.textRecommendation.maxLines=2
                    binding.buttonRecommendation.visibility=View.VISIBLE
                    binding.buttonRecommendation.text=it.text
                }?:{
                    binding.textRecommendation.maxLines=3
                    binding.buttonRecommendation.visibility=View.GONE
                }

            }
    }
}