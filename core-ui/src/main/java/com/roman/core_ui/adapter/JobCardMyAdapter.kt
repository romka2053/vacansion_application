package com.roman.core_ui.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.roman.core.BaseMyAdapter
import com.roman.core.BaseViewHolder
import com.roman.core_ui.databinding.ItemJobCardBinding
import com.roman.core.entity.VacanciesPreview
import com.roman.core_ui.R

class JobCardMyAdapter(
    maxCount: Int? = null,
    val clickLike: (id: String) -> Unit,
    val clickVacancy: (id: String) -> Unit
) :
    BaseMyAdapter<ItemJobCardBinding, List<VacanciesPreview>>(ItemJobCardBinding::inflate) {
    override val maxItemCount: Int? = maxCount

    override fun onBindViewHolder(holder: BaseViewHolder<ItemJobCardBinding>, position: Int) {
        with(holder) {
            val lookingNumber =
                itemView.context.getString(R.string.currently_viewing) + " " +
                        itemView.resources.getQuantityString(
                            R.plurals.peopleCount,
                            data[position].lookingNumber,
                            data[position].lookingNumber
                        )
            binding.countPeople.visibility = data[position].lookingNumber.let {
                if (it == 0) View.GONE else View.VISIBLE
            }
            binding.countPeople.text = lookingNumber
            binding.nameJob.text = data[position].title
            val salary = data[position].salary
            if (salary != "") {
                binding.salary.text = salary
                binding.salary.visibility = View.VISIBLE
            } else binding.salary.visibility = View.GONE
            binding.townName.text = data[position].town
            binding.firm.text = data[position].company
            binding.experience.text = data[position].experience
            val published =
                itemView.resources.getString(R.string.published) + " " + data[position].getDayMountFromDate(
                    itemView.resources.configuration.locales.get(0)
                )
            binding.dateUpdate.text = published
            val imageLike = if (data[position].isFavorite)
                R.drawable.like_active
            else
                R.drawable.like_inactive

            binding.imageLike.setImageDrawable(
                AppCompatResources.getDrawable(itemView.context, imageLike)
            )
            binding.imageLike
            binding.imageLike.setOnClickListener {
                clickLike(data[position].id)
            }
            binding.root.setOnClickListener {
                clickVacancy(data[position].id)
            }
        }
    }
}