package com.appetiser.meowvies.ui.main.adapter.recent

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.meowvies.R
import com.appetiser.meowvies.data.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_recent.view.*

/**
 * RecentViewHolder class holds the view of our GenreAdapter
 * @see RecentAdapter
 * @constructor to initialize the root view
 */
class RecentViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

    /**
     * @param movie to populate the genres
     * @param listener to handle the Listener interface
     */
    fun bind(movie: Movie.MovieList, listener: Listener?) {
        val artwork = movie.artwork
        Glide.with(root.context).load(artwork).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(
                R.drawable.ic_placeholder
            ).into(root.ivThumbnail)

        root.cvMovie.setOnClickListener {
            listener?.onVoidListener(movie)
        }
    }

    interface Listener {
        fun onVoidListener(movie: Movie.MovieList)
    }

}