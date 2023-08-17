package com.ajit.apidemo.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ajit.apidemo.data.dao.PostDao
import com.ajit.apidemo.data.model.Post
import com.ajit.apidemo.data.remote.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {

    suspend fun fetchPostsAndStoreLocallyIfNeeded() {
        val localPosts = getPostsFromLocal()
        if (localPosts.isEmpty()) {
            fetchPostsAndStoreLocally()
        }
    }

    suspend fun fetchPostsAndStoreLocally() {
        Log.e("repo", "fetching online called")

        val response = apiService.getPosts()
        if (response.isSuccessful) {
            val posts = response.body()
            Log.e("repo", "$posts")
            posts?.let {
                insertPostsToDatabase(it)
            }
        }
    }

    private suspend fun insertPostsToDatabase(posts: List<Post>) {
        withContext(Dispatchers.IO) {
            for (post in posts) {
                postDao.addPost(post)
            }
        }
    }

    fun getPagedPostsFromLocal(coroutineScope: CoroutineScope): Flow<PagingData<Post>> {
        Log.d("Repository", "Getting paged posts from local database")
        val pagingSourceFactory = { postDao.getPostsPaged() }

        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(coroutineScope)
    }


    private suspend fun getPostsFromLocal(): List<Post> {
        Log.e("repo", "local called")
        return withContext(Dispatchers.IO) {
            postDao.getPosts()
        }
    }

}