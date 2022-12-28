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
import com.yakupcan.videogameapp.databinding.HomePageViewPagerItemBinding
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.model.ViewPagerItem

class HomeFragmentViewPagerAdapter(private val listOf: List<Game>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(view: HomePageViewPagerItemBinding) : RecyclerView.ViewHolder(view.root) {
        val sentBinding: HomePageViewPagerItemBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<HomePageViewPagerItemBinding>(
            inflater,
            R.layout.home_page_view_pager_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).sentBinding.game = listOf[position]
        Picasso.get().load(listOf[position].backgroundImage).into(holder.sentBinding.viewPagerItemImg)
        holder.itemView.setOnClickListener {
            openDetailPage(listOf[position].id, it)
        }
    }

    private fun openDetailPage(id: Int, it: View?) {
        val bundle = bundleOf("setSelectedGame" to id)
        Navigation.findNavController(it!!).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun gameItemImg(
        holder: ViewHolder,
        backgroundImage: String
    ) {
        Picasso.get().load(backgroundImage).into(holder.sentBinding.viewPagerItemImg)
    }

    override fun getItemCount(): Int {
        return listOf.size
    }
}