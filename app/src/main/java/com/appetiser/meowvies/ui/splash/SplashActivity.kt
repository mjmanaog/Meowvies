package com.appetiser.meowvies.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.appetiser.meowvies.R
import com.appetiser.meowvies.ui.main.MainActivity

/**
 * SplashScreen class is the starting page of the application
 * This will be displayed in 2 secs
 */
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, 2000)
    }
}