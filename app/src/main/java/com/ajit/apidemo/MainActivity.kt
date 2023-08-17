package com.ajit.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajit.apidemo.databinding.ActivityMainBinding
import com.ajit.apidemo.adapter.PostAdapter
import com.ajit.apidemo.ui.viewmodel.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PostListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false

        }

        viewModel.refreshingState.observe(this) { isRefreshing ->
            binding.swipeRefreshLayout.isRefreshing = isRefreshing
        }

    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postAdapter

        lifecycleScope.launchWhenCreated {
            viewModel.getPagedPosts().collectLatest { pagingData ->
                postAdapter.submitData(pagingData)
            }
        }
    }
}