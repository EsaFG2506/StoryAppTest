package com.dicoding.storyapptest.di

import android.content.Context
import com.dicoding.storyapptest.data.StoryRepository
import com.dicoding.storyapptest.data.UserRepository
import com.dicoding.storyapptest.data.pref.UserPreference
import com.dicoding.storyapptest.data.pref.dataStore
import com.dicoding.storyapptest.data.retrofit.ApiConfig
import com.dicoding.storyapptest.data.room.StoryDB
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(pref, apiService)
    }

    fun provideStoryRepository(context: Context): StoryRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        val database = StoryDB.getDatabase(context)
        val dao = database.storyDao()
        return StoryRepository.getInstance(pref, apiService, dao)
    }
}