package com.yakupcan.videogameapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.databinding.FragmentDetailBinding
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
                val bundle = bundleOf()
                findNavController().navigate(R.id.action_detailFragment_to_favoriteFragment)
            }
        }
    }
}