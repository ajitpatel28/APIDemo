package com.ajit.apidemo.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajit.apidemo.data.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPost(device: Post): Long


    @Query("SELECT * FROM posts")
    fun getPostsPaged(): PagingSource<Int, Post>

    @Query("SELECT * FROM posts")
    fun getPosts(): List<Post>
}
