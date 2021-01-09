package com.appetiser.meowvies.ui.main.adapter.genre

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.view.*

/**
 * GenreViewHolder class holds the view of our GenreAdapter
 * @see GenreAdapter
 * @constructor to initialize the root view
 */
class GenreViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

    /**
     * @param genre to populate the genres
     * @param listener to handle the Listener interface
     */
    fun bind(genre: String, listener:Listener?) {
        root.tvGenre.text = genre

        root.tvGenre.setOnClickListener {
            listener?.onVoidListener(root.tvGenre.text.toString())
        }
    }

    interface Listener {
        fun onVoidListener(genre: String)
    }
}