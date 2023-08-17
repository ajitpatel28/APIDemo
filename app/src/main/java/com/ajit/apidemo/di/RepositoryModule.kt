package com.ajit.apidemo.di

import android.app.Application
import android.content.Context
import com.ajit.apidemo.data.dao.PostDao
import com.ajit.apidemo.data.remote.ApiService
import com.ajit.apidemo.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService, postDao: PostDao): PostRepository {
        return PostRepository(apiService, postDao)
    }

}