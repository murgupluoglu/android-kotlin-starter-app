package com.murgupluoglu.kotlinmvvm.activity.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.murgupluoglu.kotlinmvvm.databinding.ActivityProductDetailBinding
import com.murgupluoglu.kotlinmvvm.utils.viewBinding
import com.murgupluoglu.request.Request
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {

    companion object {
        private val PARAM_SEED_ID = "PARAM_SEED_ID"
        fun newInstance(context: Context, seedId: String): Intent {
            return Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(PARAM_SEED_ID, seedId)
            }
        }
    }

    private val binding by viewBinding(ActivityProductDetailBinding::inflate)

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val seedId = intent.extras!!.getString(PARAM_SEED_ID)!!
        setupObservers()
        viewModel.getPerson(seedId)
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

                        binding.nameTextView.text =
                            "id: ${uiState.data.id} name: ${uiState.data.title}"
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
}