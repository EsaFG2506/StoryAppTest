package com.dicoding.storyapptest.views.addstory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.storyapptest.data.StoryRepository
import kotlinx.coroutines.launch
import java.io.File

class StoryViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun uploadStories(imageFile: File, description: String) = storyRepository.uploadStories(imageFile, description)

    fun logout() {
        viewModelScope.launch {
            storyRepository.logout()
            Log.d(TAG, "Token removed")
        }
    }

    companion object {
        private const val TAG = "AddStoryViewModel"
    }
}