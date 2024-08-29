package com.roman.feature_favorite.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.roman.core.BaseViewHolder
import com.roman.core.entity.VacanciesPreview
import com.roman.core_ui.databinding.ItemJobCardBinding

class FavoriteAdapter(
    private val clickLike: (id: String) -> Unit,
    private val clickVacancy: (id: String) -> Unit
) : ListAdapter<VacanciesPreview, BaseViewHolder<ItemJobCardBinding>>(DiffUtilItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemJobCardBinding> {
        val binding = ItemJobCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemJobCardBinding>, position: Int) {
        val data = getItem(position)
        with(holder) {
            val lookingNumber =
                itemView.context.getString(com.roman.core_ui.R.string.currently_viewing) + " " +
                        itemView.resources.getQuantityString(
                            com.roman.core_ui.R.plurals.peopleCount,
                            data.lookingNumber,
                            data.lookingNumber
                        )
            binding.countPeople.visibility = data.lookingNumber.let {
                if (it == 0) View.GONE else View.VISIBLE
            }
            binding.countPeople.text = lookingNumber
            binding.nameJob.text = data.title
            val salary = data.salary
            if (salary != "") {
                binding.salary.text = salary
                binding.salary.visibility = View.VISIBLE
            } else binding.salary.visibility = View.GONE
            binding.townName.text = data.town
            binding.firm.text = data.company
            binding.experience.text = data.experience
            val published =
                itemView.resources.getString(com.roman.core_ui.R.string.published) + " " + data.getDayMountFromDate(
                    itemView.resources.configuration.locales.get(0)
                )
            binding.dateUpdate.text = published
            val imageLike = if (data.isFavorite)
                com.roman.core_ui.R.drawable.like_active
            else
                com.roman.core_ui.R.drawable.like_inactive

            binding.imageLike.setImageDrawable(
                AppCompatResources.getDrawable(itemView.context, imageLike)
            )

            binding.imageLike.setOnClickListener {
                clickLike(data.id)
            }
            binding.root.setOnClickListener {
                clickVacancy(data.id)
            }
        }
        getItem(position)
    }

}

class DiffUtilItemCallback : DiffUtil.ItemCallback<VacanciesPreview>() {
    override fun areItemsTheSame(oldItem: VacanciesPreview, newItem: VacanciesPreview): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VacanciesPreview, newItem: VacanciesPreview): Boolean {
        return oldItem == newItem
    }
}