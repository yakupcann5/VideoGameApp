package com.yakupcan.videogameapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.yakupcan.videogameapp.R
import com.yakupcan.videogameapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //c6c664fcb4414e2a951c2d445e348cc7
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView() {
        binding.botNav.setOnItemSelectedListener { menuItem ->
            when (viewModel.getCurrentFragment()) {
                "home" -> {
                    when (menuItem.itemId) {
                        R.id.navigation_fav -> {
                            findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_favoriteFragment)
                        }
                    }
                    true
                }
                "fav" -> {
                    when (menuItem.itemId) {
                        R.id.navigation_home -> {
                            findNavController(R.id.nav_host_fragment).navigate(R.id.action_favoriteFragment_to_homeFragment)
                        }
                    }
                    true
                }
                else -> false
            }
        }
    }
}