package com.dicoding.storyapptest.views.signup

import androidx.lifecycle.ViewModel
import com.dicoding.storyapptest.data.UserRepository

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun register(name: String, email: String, password: String) = userRepository.register(name, email, password)
}