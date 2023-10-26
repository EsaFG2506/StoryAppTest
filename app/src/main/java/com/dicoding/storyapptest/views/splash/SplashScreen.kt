package com.dicoding.storyapptest.views.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.*
import androidx.lifecycle.lifecycleScope
import com.dicoding.storyapptest.R
import com.dicoding.storyapptest.data.pref.UserPreference
import com.dicoding.storyapptest.data.pref.dataStore
import com.dicoding.storyapptest.views.main.MainActivity
import com.dicoding.storyapptest.views.welcome.WelcomeActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())
    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        pref = UserPreference.getInstance(this.dataStore)

        handler.postDelayed({
            lifecycleScope.launch {
                val userModel = pref.getSession().first()
                if (userModel.isLogin) {
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashScreen, WelcomeActivity::class.java))
                }
                finish()
            }
        },3000)
    }
}