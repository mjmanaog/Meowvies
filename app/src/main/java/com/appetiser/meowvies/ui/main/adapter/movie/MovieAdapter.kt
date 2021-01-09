package com.appetiser.meowvies.ui.main.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.meowvies.R
import com.appetiser.meowvies.data.model.Movie

/**
 * MovieAdapter class to create an adapter for our recyclerview for genre
 */
class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList: MutableList<Movie.MovieList> = mutableListOf()

    private var listener: MovieViewHolder.Listener? = null

    /**
     * @param listener to handle the Listener interface in the MovieViewHolder
     * @see MovieViewHolder
     */
    fun setListener(listener: MovieViewHolder.Listener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(
            layoutInflater
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position),listener)
    }

    /**
     * @return position of the item
     */
    private fun getItem(position: Int): Movie.MovieList {
        return movieList[position]
    }

    /**
     * @param movieList to add all the list's content
     */
    fun addAll(movieList: List<Movie.MovieList>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

}