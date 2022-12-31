package com.yakupcan.videogameapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.databinding.HomePageSearchResultItemBinding
import com.yakupcan.videogameapp.domain.model.Game

class HomeFragmentSearchResultAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<Game> = arrayListOf()

    class ViewHolder(itemView: HomePageSearchResultItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val sentBinding: HomePageSearchResultItemBinding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HomePageSearchResultItemBinding>(
            inflater,
            R.layout.home_page_search_result_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).sentBinding.searchGame = items[position]
        gameItemImg(holder, items[position].backgroundImage)
        holder.itemView.setOnClickListener {
            openDetailPage(items[position].id, it)
        }
    }

    private fun openDetailPage(id: Int, it: View?) {
        val bundle = bundleOf("setSelectedGame" to id)
        Navigation.findNavController(it!!)
            .navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun gameItemImg(
        holder: ViewHolder,
        backgroundImage: String
    ) {
        Picasso.get().load(backgroundImage).into(holder.sentBinding.homeRecyclerViewSearchItemImage)
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