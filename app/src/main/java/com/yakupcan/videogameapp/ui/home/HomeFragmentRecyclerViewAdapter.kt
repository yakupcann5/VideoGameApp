package com.yakupcan.videogameapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.databinding.HomePageRecyclerViewItemBinding
import com.yakupcan.videogameapp.domain.model.Game

class HomeFragmentRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<Game> = arrayListOf()

    class ViewHolder(view: HomePageRecyclerViewItemBinding) : RecyclerView.ViewHolder(view.root) {
        val sentBinding: HomePageRecyclerViewItemBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HomePageRecyclerViewItemBinding>(
            inflater,
            R.layout.home_page_recycler_view_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).sentBinding.game = items[position]
        gameItemImg(holder, items[position].backgroundImage)
        holder.itemView.setOnClickListener {

        }
    }

    private fun gameItemImg(
        holder: ViewHolder,
        backgroundImage: String
    ) {
        Picasso.get().load(backgroundImage).into(holder.sentBinding.homeRecyclerViewItemImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(gameList: ArrayList<Game>) {
        items.clear()
        items.addAll(gameList)
        notifyDataSetChanged()
    }
}