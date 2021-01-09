package com.appetiser.meowvies.ui.main.adapter.movie

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.meowvies.R
import com.appetiser.meowvies.data.model.Movie
import com.appetiser.meowvies.helper.DateHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * MovieViewHolder class holds the view of our GenreAdapter
 * @see MovieAdapter
 * @constructor to initialize the root view
 */
class MovieViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

    /**
     * @param movie to populate the movies
     * @param listener to handle the Listener interface
     */
    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie.MovieList, listener: Listener?) {
        val genre = movie.genre
        val title = movie.trackName
        val price = movie.price.toString()
        val artwork = movie.artwork
        movie.dateVisited = DateHelper.getCurrentDateTime().toString()
        root.tvGenre.text = genre
        root.tvTitle.text = title
        root.tvPrice.text = """$$price"""
        Glide.with(root.context).load(artwork).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.ic_placeholder).into(root.ivThumbnail)

        root.cvMovie.setOnClickListener {
            listener?.onVoidListener(movie)
        }
    }

    interface Listener {
        fun onVoidListener(movie: Movie.MovieList)
    }

}