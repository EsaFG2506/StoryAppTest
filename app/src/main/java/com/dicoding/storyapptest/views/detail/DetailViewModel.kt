package com.dicoding.storyapptest.views.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.storyapptest.data.StoryRepository
import kotlinx.coroutines.launch

class DetailViewModel (private val storyRepository: StoryRepository) : ViewModel() {
    fun getDetailStories(id: String) = storyRepository.getDetailStories(id)

    fun logout() {
        viewModelScope.launch {
            storyRepository.logout()
            Log.d(TAG, "Token removed")
        }
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}