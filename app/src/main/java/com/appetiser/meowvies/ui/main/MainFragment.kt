package com.appetiser.meowvies.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appetiser.meowvies.R
import com.appetiser.meowvies.data.model.Movie
import com.appetiser.meowvies.helper.Constants.REQ_MOVIE_INFO
import com.appetiser.meowvies.helper.DialogHelper
import com.appetiser.meowvies.ui.main.adapter.genre.GenreAdapter
import com.appetiser.meowvies.ui.main.adapter.genre.GenreViewHolder
import com.appetiser.meowvies.ui.main.adapter.movie.MovieAdapter
import com.appetiser.meowvies.ui.main.adapter.movie.MovieViewHolder
import com.appetiser.meowvies.ui.main.adapter.recent.RecentAdapter
import com.appetiser.meowvies.ui.main.adapter.recent.RecentViewHolder
import com.appetiser.meowvies.ui.movieInfo.MovieInfoActivity
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * MainFragment class where the rendering of data from the ViewModel to UI happens
 */
class MainFragment : Fragment() {

    /**
     * Initializes the needed objects/variable
     */
    private lateinit var mainViewModel: MainViewModel

    private var movieAdapter: MovieAdapter =
        MovieAdapter()
    private var genreAdapter: GenreAdapter =
        GenreAdapter()
    private var recentAdapter: RecentAdapter =
        RecentAdapter()

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun initViewModel() {
        mainViewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun setupUI() {

        /**
         * Setup Movie RecyclerView
         */
        rvMain.layoutManager = GridLayoutManager(context, 2)
        rvMain.adapter = movieAdapter
        /**
         * Applies the listener from the MovieViewHolder and triggers any process
         * @see MovieViewHolder
         */
        rvMain.apply {
            movieAdapter.setListener(object : MovieViewHolder.Listener {
                override fun onVoidListener(
                    movie: Movie.MovieList
                ) {
                    mainViewModel.insertRecent(movie)
                    startActivityForResult(
                        MovieInfoActivity.getIntent(
                            requireActivity(),
                            movie.trackId,
                            movie.trackName,
                            movie.genre,
                            movie.price.toString(),
                            movie.longDescription,
                            movie.artwork
                        ), REQ_MOVIE_INFO
                    )
                }
            })
        }
        setMovieList()

        /**
         * Setup Genre RecyclerView
         */
        rvGenres.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvGenres.adapter = genreAdapter
        /**
         * Applies the listener from the GenreViewHolder and triggers any process
         * @see GenreViewHolder
         */
        rvGenres.apply {
            genreAdapter.setListener(object : GenreViewHolder.Listener {
                override fun onVoidListener(genre: String) {
                    tvSelectedGenre.text = genre
                    mainViewModel.getSpecificGenre(genre, false)
                    showGenres(false)
                }
            })
        }

        /**
         * Setup Recent RecyclerView
         */
        rvRecentMovies.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        rvRecentMovies.adapter = recentAdapter
        /**
         * Applies the listener from the RecentViewHolder and triggers any process
         * @see RecentViewHolder
         */
        rvRecentMovies.apply {
            recentAdapter.setListener(object : RecentViewHolder.Listener {
                override fun onVoidListener(movie: Movie.MovieList) {
                    startActivityForResult(
                        MovieInfoActivity.getIntent(
                            requireActivity(),
                            movie.trackId,
                            movie.trackName,
                            movie.genre,
                            movie.price.toString(),
                            movie.longDescription,
                            movie.artwork
                        ), REQ_MOVIE_INFO
                    )
                }
            })
        }

        tvAllGenre.setOnClickListener {
            tvSelectedGenre.text = getString(R.string.title_genre)
            mainViewModel.getSpecificGenre("", true)
            showGenres(false)
        }

        btnClose.setOnClickListener {
            showGenres(false)
        }
        llGenre.setOnClickListener {
            showGenres(true)
        }

        tvRetry.setOnClickListener {
            Log.e(">>", "aaaa")
            setMovieList()
        }

        /**
         * Observe changes in the list of movies if any then it will automatically update the main recyclerview
         */
        mainViewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
            showProgressBar(false)
            movieAdapter.addAll(movieList)
        })

        /**
         * Observe changes in the list of genres if any then it will automatically update the genre recyclerview
         */
        mainViewModel.genreList.observe(viewLifecycleOwner, Observer { genreList ->
            genreAdapter.addAll(genreList)
        })

        /**
         * Observe changes in the list of specific genres if any then it will automatically update the main recyclerview
         */
        mainViewModel.specificGenreList.observe(viewLifecycleOwner, Observer { specificGenreList ->
            movieAdapter.addAll(specificGenreList)
        })

        /**
         * Observe isSuccessful boolean, whenever the api call success or not in the API call this will be triggered
         * Display a dialog when error occurs
         * Enable/Disable buttons depending on the result
         */
        mainViewModel.isSuccessfull.observe(viewLifecycleOwner, Observer { successful ->
            showProgressBar(false)
            isSuccessfullyLoaded(successful)
            if (!successful) {
                DialogHelper.createOkCancelDialog(
                    requireContext(),
                    getString(R.string.error_unable_to_connect),
                    getString(R.string.action_retry),
                    getString(R.string.action_later),
                    false,
                    { setMovieList() },
                    {})?.show()
            }

        })

        /**
         * Observe changes in the list of recently viewed movies if any then it will automatically update the recent recyclerview
         */
        mainViewModel.allRecent?.observe(viewLifecycleOwner, Observer { recentList ->
            if (recentList.isNotEmpty())
                clRecentMovies.visibility = View.VISIBLE
            recentAdapter.addAll(recentList)
        })

    }

    /**
     * Get movie list
     */
    private fun setMovieList() {
        showProgressBar(true)
        context?.let {
            mainViewModel.getMovies("star", "au", "movie", {
                showProgressBar(false)
            }, {
                showProgressBar(false)
            })
        }
    }

    /**
     * Show or hide all genres
     */
    private fun showGenres(show: Boolean) {
        if (show) clShowGenres.visibility = View.VISIBLE
        else clShowGenres.visibility = View.GONE
    }

    /**
     * Show or hide progress
     */
    private fun showProgressBar(show: Boolean) {
        if (show) llProgressBar.visibility = View.VISIBLE
        else llProgressBar.visibility = View.GONE
    }

    /**
     * Show or hide retry message
     * Enable or disable genre selection button
     * Add anything here
     */
    private fun isSuccessfullyLoaded(success: Boolean){
        if (success){
            llGenre.isEnabled = true
            tvRetry.visibility = View.GONE
        }else{
            llGenre.isEnabled = false
            tvRetry.visibility = View.VISIBLE
        }

    }
}