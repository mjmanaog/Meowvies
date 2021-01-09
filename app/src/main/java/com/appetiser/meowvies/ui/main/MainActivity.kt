package com.appetiser.meowvies.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appetiser.meowvies.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

/**
 * MainActivity class work as a container that renders the MainFragment
 * @see MainFragment
 */
class MainActivity : AppCompatActivity(){
    companion object{
        /**
         * @param context to receive the activity context
         */
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        includeToolbar.title.text = resources.getString(R.string.app_name)

        if (savedInstanceState == null) {
            val fragment = MainFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.llContainer, fragment).commit()
        }
    }
}