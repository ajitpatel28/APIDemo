package com.ajit.apidemo.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.ajit.apidemo.data.model.Post
import com.ajit.apidemo.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {


    private val _refreshingState = MutableLiveData<Boolean>()
    val refreshingState: LiveData<Boolean>
        get() = _refreshingState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.fetchPostsAndStoreLocallyIfNeeded()
        }
    }

    fun getPagedPosts(): Flow<PagingData<Post>> {
        return postRepository.getPagedPostsFromLocal(viewModelScope)
    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            _refreshingState.postValue(true)
            postRepository.fetchPostsAndStoreLocally()
            _refreshingState.postValue(false)
        }
    }
}