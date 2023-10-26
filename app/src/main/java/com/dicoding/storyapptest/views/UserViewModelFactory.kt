package com.dicoding.storyapptest.views

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.storyapptest.data.UserRepository
import com.dicoding.storyapptest.di.Injection
import com.dicoding.storyapptest.views.login.LoginViewModel
import com.dicoding.storyapptest.views.signup.SignUpViewModel

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): UserViewModelFactory {
            if (INSTANCE == null) {
                synchronized(UserViewModelFactory::class.java) {
                    INSTANCE = UserViewModelFactory(Injection.provideUserRepository(context))
                }
            }
            return INSTANCE as UserViewModelFactory
        }
    }
}