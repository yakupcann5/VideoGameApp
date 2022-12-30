package com.yakupcan.videogameapp.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.databinding.FavoriteFragmentItemBinding
import com.yakupcan.videogameapp.db.entities.FavoriteGameEntities
import com.yakupcan.videogameapp.domain.model.Game

class FavoriteRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val favGameItem: ArrayList<Game> = arrayListOf()

    class ViewHolder(view: FavoriteFragmentItemBinding): RecyclerView.ViewHolder(view.root) {
        val sentBinding: FavoriteFragmentItemBinding = view
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<FavoriteFragmentItemBinding>(
            inflater,
            R.layout.favorite_fragment_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).sentBinding.favGame = favGameItem[position]
        favGameItemImg(holder, favGameItem[position].backgroundImage)

    }

    private fun favGameItemImg(
        holder: ViewHolder,
        backgroundImage: String
    ) {
        Picasso.get().load(backgroundImage).into(holder.sentBinding.favRecyclerViewItemImage)
    }


    override fun getItemCount(): Int {
        return favGameItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(favList: ArrayList<Game>) {
        favGameItem.clear()
        favGameItem.addAll(favList)
        notifyDataSetChanged()
    }
}