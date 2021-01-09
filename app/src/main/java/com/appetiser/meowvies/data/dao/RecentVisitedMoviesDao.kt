package com.appetiser.meowvies.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appetiser.meowvies.data.model.Movie
/**
 * Create queries using DAO to have an interaction between the data and movie table
 */
@Dao
interface RecentVisitedMovieDao{

    /**
     * Insert movie's information to local db
     * @param movie to insert the Movie.MovieList's data
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie.MovieList)

    /**
     * Get all the saved movies in the local db
     * @return[LiveData<List<Movie.List>>] returns the list of recent movies saved in the local db
     */
    @Query("SELECT * FROM movie_list ORDER BY date(dateVisited) DESC")
    fun getRecentVisitedList(): LiveData<List<Movie.MovieList>>
}