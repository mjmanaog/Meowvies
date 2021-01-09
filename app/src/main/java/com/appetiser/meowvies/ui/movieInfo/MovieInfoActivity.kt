package com.appetiser.meowvies.ui.movieInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.appetiser.meowvies.R
import kotlinx.android.synthetic.main.activity_movie_info.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

/**
 * MovieInfoActivity class work as a container that renders the MainFragment
 * @see MovieInfoFragment
 */
class MovieInfoActivity : AppCompatActivity() {
    companion object {
        /**
         * This method uses intent bundles to pass data to the fragment
         * @param context to receive the activity context
         * @param id get the movie's id
         * @param title get the movie's title
         * @param genre get the movie's genre
         * @param price get the movie's price
         * @param longDesc get the movie's longDesc
         * @param artwork get the movie's artwork
         */
        fun getIntent(
            context: Context,
            id: Int,
            title: String,
            genre: String,
            price: String,
            longDesc: String,
            artwork: String?
        ): Intent {
            val intent = Intent(context, MovieInfoActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("genre", genre)
            intent.putExtra("title", title)
            intent.putExtra("price", price)
            intent.putExtra("longDesc", longDesc)
            intent.putExtra("artwork", artwork ?: "")
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)

        setSupportActionBar(includeToolbar as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        includeToolbar.title.text = resources.getString(R.string.title_movie_info)

        /**
         * Getting the available extras and pass it to the fragment
         */
        if (savedInstanceState == null) {
            val fragment = MovieInfoFragment.newInstance(
                intent.getIntExtra("id", 0),
                intent.getStringExtra("genre"),
                intent.getStringExtra("title"),
                intent.getStringExtra("price"),
                intent.getStringExtra("longDesc"),
                intent.getStringExtra("artwork")
            )
            supportFragmentManager.beginTransaction().replace(R.id.llContainer, fragment).commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}