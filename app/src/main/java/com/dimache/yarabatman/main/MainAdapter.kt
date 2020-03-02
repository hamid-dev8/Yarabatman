package com.dimache.yarabatman.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimache.yarabatman.BR
import com.dimache.yarabatman.data.model.Movies
import com.dimache.yarabatman.databinding.ItemMoviesBinding
import kotlinx.android.synthetic.main.item_movies.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    var list : List<Movies> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: (Movies, View) -> Unit = { category, view ->  }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMoviesBinding = ItemMoviesBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(val binding : ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item : Movies)
        {
            binding.setVariable(BR.movies,item)
            binding.executePendingBindings()

            itemView.txt_detail.setOnClickListener {
                onItemClick(item,itemView)
            }
        }
    }

}