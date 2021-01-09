package com.appetiser.meowvies.data.database

import androidx.lifecycle.LiveData
import com.appetiser.meowvies.data.dao.RecentVisitedMovieDao
import com.appetiser.meowvies.data.model.Movie

/**
 * To encapsulate the needed methods from our DAO
 * @constructor[dao] gives access to
 * @see RecentVisitedMovieDao
 */
class AppDatabaseManager(private val dao: RecentVisitedMovieDao){
    /**
     * Suspends - marked this function as coroutinable (is that the right term? lol)
     * @param[movie] refers to the Movie.MovieList, to pass it to the insertMovie() method
     */
    suspend fun saveMovie(movie: Movie.MovieList){
        dao.insertMovie(movie)
    }

    val allRecentVisited: LiveData<List<Movie.MovieList>> = dao.getRecentVisitedList()
}