package com.yakupcan.videogameapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.databinding.FragmentHomeBinding
import com.yakupcan.videogameapp.domain.model.Game
import com.yakupcan.videogameapp.domain.model.ViewPagerItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val TAG = "HomeFragment"
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var gameAdapter: HomeFragmentRecyclerViewAdapter
    private lateinit var viewPagerAdapter: HomeFragmentViewPagerAdapter
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
        val viewPagerList = arrayListOf<Game>()
        viewModel.gameList.observe(viewLifecycleOwner) { list ->
            list.forEach {
                if (viewPagerList.size < 3) {
                    viewPagerList.add(it)
                } else {
                    gameList.add(it)
                }
            }
            gameAdapter.setList(gameList)
            initViewPager(viewPagerList)
        }
    }

    private fun initRecyclerView() {
        gameAdapter = HomeFragmentRecyclerViewAdapter()
        binding.homeRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.homeRecycler.adapter = gameAdapter
        getGame()
    }

    private fun initViewPager(viewPagerList: ArrayList<Game>) {
        viewPagerAdapter = HomeFragmentViewPagerAdapter(
            listOf(
                Game(
                    viewPagerList[0].backgroundImage,
                    viewPagerList[0].id,
                ),
                ViewPagerItem(
                    viewPagerList[1].backgroundImage,
                    viewPagerList[1].id,
                ),
                ViewPagerItem(
                    viewPagerList[2].backgroundImage,
                    viewPagerList[2].id,
                )
            ) as List<Game>
        )
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {

            }
        }.attach()
    }
}