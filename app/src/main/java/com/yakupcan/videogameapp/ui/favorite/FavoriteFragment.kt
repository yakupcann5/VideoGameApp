package com.yakupcan.videogameapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.databinding.FragmentFavoriteBinding
import com.yakupcan.videogameapp.db.entities.FavoriteGameEntities
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.ui.home.HomeFragmentRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private var adapter = FavoriteRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCurrentFragment("fav")
        Constants.getBottomViewVisibility(true, requireActivity())
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = FavoriteRecyclerViewAdapter()
        binding.favoriteRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.favoriteRecyclerView.adapter = adapter
        getFavoriteGames()
    }

    private fun getFavoriteGames() {
        viewModel.getFavoriteGames()
        viewModel.allFavGame.observe(viewLifecycleOwner) { list->
            adapter.setList(list)
        }
    }
}