package com.ajit.apidemo.di

import android.content.Context
import androidx.room.Room
import com.ajit.apidemo.data.dao.PostDao
import com.ajit.apidemo.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "post_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideDeviceDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }
}
