package com.yakupcan.videogameapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.databinding.FragmentHomeBinding
import com.yakupcan.videogameapp.domain.model.Game
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val TAG = "HomeFragment"
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var gameAdapter: HomeFragmentRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCurrentFragment("home")
        Constants.getBottomViewVisibility(true, requireActivity())
        initRecyclerView()
    }

    private fun getGame() {
        viewModel.getGames()
        val gameList = ArrayList<Game>()
        viewModel.gameList.observe(viewLifecycleOwner) { list ->
            list.forEach {
                gameList.add(it)
                gameAdapter.setList(gameList)
            }
        }
    }

    private fun initRecyclerView() {
        gameAdapter = HomeFragmentRecyclerViewAdapter()
        binding.homeRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.homeRecycler.adapter = gameAdapter
        getGame()
    }
}