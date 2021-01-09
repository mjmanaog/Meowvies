package com.appetiser.meowvies.ui.main.adapter.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.meowvies.R
import com.appetiser.meowvies.data.model.Movie

/**
 * RecentAdapter class to create an adapter for our recyclerview for genre
 */
class RecentAdapter : RecyclerView.Adapter<RecentViewHolder>() {

    private var recentList: MutableList<Movie.MovieList> = mutableListOf()

    private var listener: RecentViewHolder.Listener? = null

    /**
     * @param listener to handle the Listener interface in the MovieViewHolder
     * @see RecentViewHolder
     */
    fun setListener(listener: RecentViewHolder.Listener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent, parent, false)
        return RecentViewHolder(
            layoutInflater
        )
    }

    override fun getItemCount(): Int {
        return recentList.size
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(getItem(position),listener)
    }

    /**
     * @return position of the item
     */
    private fun getItem(position: Int): Movie.MovieList {
        return recentList[position]
    }

    /**
     * @param recentList to add all the list's content
     */
    fun addAll(recentList: List<Movie.MovieList>) {
        this.recentList.clear()
        this.recentList.addAll(recentList)
        notifyDataSetChanged()
    }

}