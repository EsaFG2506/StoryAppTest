package com.dicoding.storyapptest.views.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.storyapptest.data.StoryRepository
import com.dicoding.storyapptest.data.UserRepository
import com.dicoding.storyapptest.data.pref.UserModel
import kotlinx.coroutines.launch

class MainViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return storyRepository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            storyRepository.logout()
            Log.d(TAG, "Token removed")
        }
    }

    fun getStories() = storyRepository.getStories()

    companion object {
        private const val TAG = "MainViewModel"
    }
}