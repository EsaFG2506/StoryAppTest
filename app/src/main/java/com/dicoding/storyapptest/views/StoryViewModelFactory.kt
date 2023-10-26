package com.dicoding.storyapptest.views

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.storyapptest.data.StoryRepository
import com.dicoding.storyapptest.di.Injection
import com.dicoding.storyapptest.views.addstory.StoryViewModel
import com.dicoding.storyapptest.views.detail.DetailViewModel
import com.dicoding.storyapptest.views.main.MainViewModel

class StoryViewModelFactory(private val storyRepository: StoryRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(storyRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(storyRepository) as T
            }
            modelClass.isAssignableFrom(StoryViewModel::class.java) -> {
                StoryViewModel(storyRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: StoryViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): StoryViewModelFactory {
            if (INSTANCE == null) {
                synchronized(StoryViewModelFactory::class.java) {
                    INSTANCE = StoryViewModelFactory(Injection.provideStoryRepository(context))
                }
            }
            return INSTANCE as StoryViewModelFactory
        }
    }
}