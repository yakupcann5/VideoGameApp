package com.yakupcan.videogameapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.databinding.FragmentDetailBinding
import com.yakupcan.videogameapp.common.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Constants.getBottomViewVisibility(false, requireActivity())
        getDataFromApi()
        initView()
    }

    private fun initView() {
        binding.detailFragmentFavButton.setOnClickListener(this)
        binding.detailFragmentNotFavButton.setOnClickListener(this)
        isGameFavorite(arguments?.getInt("setSelectedGame")!!)
    }

    private fun isGameFavorite(int: Int) {
        lifecycleScope.launch {
            viewModel.gameIsFavorite(int)
            viewModel.gameIsFavorited.observe(viewLifecycleOwner) {
                if (it) {
                    binding.detailFragmentFavButton.visibility = View.GONE
                    binding.detailFragmentNotFavButton.visibility = View.VISIBLE
                } else {
                    binding.detailFragmentFavButton.visibility = View.VISIBLE
                    binding.detailFragmentNotFavButton.visibility = View.GONE
                }
            }
        }
    }

    private fun getDataFromApi() {
        viewModel.getSingleGame(arguments?.getInt("setSelectedGame")!!)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.singleGame.collect { requestState ->
                requestState?.data.let {
                    binding.game = it
                    detailImg(it?.backgroundImage)
                }
            }
        }
    }

    private fun detailImg(backgroundImage: String?) {
        Picasso.get().load(backgroundImage).into(binding.detailFragmentImage)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.detail_fragment_fav_button -> {
                viewModel.addToFav(arguments?.getInt("setSelectedGame"))
                binding.detailFragmentFavButton.visibility = View.GONE
                binding.detailFragmentNotFavButton.visibility = View.VISIBLE
            }
            R.id.detail_fragment_not_fav_button -> {
                viewModel.deleteToFav(arguments?.getInt("setSelectedGame"))
                binding.detailFragmentFavButton.visibility = View.VISIBLE
                binding.detailFragmentNotFavButton.visibility = View.GONE
            }
        }
    }
}