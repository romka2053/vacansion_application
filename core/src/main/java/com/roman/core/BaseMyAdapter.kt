package com.roman.core

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.roman.core.entity.Vacancy

abstract class BaseMyAdapter<VB : ViewBinding, D : List<Any>>
    (private val inflate: Inflate<VB>) : Adapter<BaseViewHolder<VB>>() {

    private var _data: D? = null
    val data get() = _data!!
    fun setData(dataNew: D) {
        _data = dataNew
        notifyDataSetChanged()
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = inflate.invoke(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BaseViewHolder(binding)
    }

    open val maxItemCount: Int? = null
    override fun getItemCount(): Int = when {
        _data == null -> {
            0
        }

        maxItemCount == null -> {
            _data!!.size
        }

        maxItemCount!! > _data!!.size -> {
            Log.d("1111", ">")
            _data!!.size
        }

        else -> {
            maxItemCount!!
        }

    }


    abstract override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int)


}

class BaseViewHolder<VB : ViewBinding>(val binding: VB) : ViewHolder(binding.root) {
}


