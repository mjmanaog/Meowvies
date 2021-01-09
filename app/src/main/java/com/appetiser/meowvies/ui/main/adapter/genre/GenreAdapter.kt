package com.appetiser.meowvies.ui.main.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.meowvies.R

/**
 * GenreAdapter class to create an adapter for our recyclerview for genre
 */
class GenreAdapter : RecyclerView.Adapter<GenreViewHolder>() {

    private var genreList: MutableList<String> = mutableListOf()

    private var listener: GenreViewHolder.Listener? = null

    /**
     * @param listener to handle the Listener interface in the MovieViewHolder
     * @see GenreViewHolder
     */
    fun setListener(listener: GenreViewHolder.Listener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(
            layoutInflater
        )
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    /**
     * @return position of the item
     */
    private fun getItem(position: Int): String {
        return genreList[position]
    }

    /**
     * @param genreList to add all the list's content
     */
    fun addAll(genreList: List<String>) {
        this.genreList.clear()
        this.genreList.addAll(genreList)
        notifyDataSetChanged()
    }
}