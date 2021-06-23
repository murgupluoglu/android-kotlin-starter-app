package com.murgupluoglu.kotlinmvvm.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.blankj.utilcode.util.LogUtils
import com.murgupluoglu.kotlinmvvm.R
import com.murgupluoglu.kotlinmvvm.api.response.PersonModel
import com.murgupluoglu.kotlinmvvm.databinding.ActivityMainBinding
import com.murgupluoglu.kotlinmvvm.utils.viewBinding
import com.murgupluoglu.request.Request
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val viewModel: MainViewModel by viewModels()

    private lateinit var personAdapter: PersonAdapter
    private var peoples = ArrayList<PersonModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupAdapter()
        setupObservers()
        viewModel.getPeoples()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is Request.Loading -> {
                        LogUtils.d("Loading", uiState.isLoading)
                        if (uiState.isLoading) {
                            //binding.loadingLayout.visibility = View.VISIBLE
                        } else {
                            //binding.loadingLayout.visibility = View.GONE
                        }
                    }
                    is Request.Success -> {
                        LogUtils.d("Success", uiState.data)

                        peoples.addAll(uiState.data.results)
                        personAdapter.notifyDataSetChanged()
                    }
                    is Request.Error -> {
                        LogUtils.d("Error", uiState.error)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        personAdapter = PersonAdapter(peoples) { person ->
            LogUtils.d("Clicked", person)
        }
        binding.peopleRecyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        binding.peopleRecyclerView.adapter = personAdapter
    }
}