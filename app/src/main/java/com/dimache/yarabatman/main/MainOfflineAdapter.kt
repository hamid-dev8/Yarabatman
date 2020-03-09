package com.dimache.yarabatman.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimache.yarabatman.BR
import com.dimache.yarabatman.data.model.CategoryModel
import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.databinding.ItemMoviesOfflineBinding

class MainOfflineAdapter() : RecyclerView.Adapter<MainOfflineAdapter.ViewHolder>()
{

    var list : List<CategoryModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainOfflineAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMoviesOfflineBinding = ItemMoviesOfflineBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainOfflineAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(val binding : ItemMoviesOfflineBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : CategoryModel)
        {
            binding.setVariable(BR.cat,item)
            binding.executePendingBindings()
        }
    }
}