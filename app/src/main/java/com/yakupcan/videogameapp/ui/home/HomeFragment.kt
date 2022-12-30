package com.yakupcan.videogameapp.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
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
    private lateinit var viewPagerAdapter: HomeFragmentViewPagerAdapter
    private lateinit var searchResultAdapter: HomeFragmentSearchResultAdapter
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
        searchGame()
    }

    private fun searchGame() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    gameAdapter.setList(arrayListOf())
                    initRecyclerView()
                } else {
                    if (s.length > 2) {
                        viewModel.getContentsFromDB(s.toString())
                        afterSearchinitRecyclerView()
                    }
                }
            }
        })
    }

    private fun getGame() {
        viewModel.refresh()
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

    fun afterSearchinitRecyclerView() {
        searchResultAdapter = HomeFragmentSearchResultAdapter()
        binding.searchResultsRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.searchResultsRecycler.adapter = searchResultAdapter
        binding.viewPagerLinear.visibility = View.GONE
        binding.homeRecyclerLinear.visibility = View.GONE
        binding.searchResultsLinear.visibility = View.VISIBLE
        viewModel.searchList.observe(viewLifecycleOwner) { list ->
            if (list.isEmpty()) {
                binding.searchResultsLinear.visibility = View.GONE
                binding.noResultText.visibility = View.VISIBLE
            } else {
                binding.searchResultsLinear.visibility = View.VISIBLE
                binding.noResultText.visibility = View.GONE
                searchResultAdapter.setList(viewModel.entitiesConvertGame(list))
            }
        }
    }

    private fun initViewPager(viewPagerList: ArrayList<Game>) {
        viewPagerAdapter = HomeFragmentViewPagerAdapter(
            listOf(
                Game(
                    viewPagerList[0].id,
                    viewPagerList[0].name,
                    viewPagerList[0].released,
                    viewPagerList[0].backgroundImage,
                    viewPagerList[0].rating,
                    viewPagerList[0].ratingTop,
                ),
                Game(
                    viewPagerList[1].id,
                    viewPagerList[1].name,
                    viewPagerList[1].released,
                    viewPagerList[1].backgroundImage,
                    viewPagerList[1].rating,
                    viewPagerList[1].ratingTop,
                ),
                Game(
                    viewPagerList[2].id,
                    viewPagerList[2].name,
                    viewPagerList[2].released,
                    viewPagerList[2].backgroundImage,
                    viewPagerList[2].rating,
                    viewPagerList[2].ratingTop,
                )
            )
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