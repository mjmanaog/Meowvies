package com.appetiser.meowvies.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appetiser.meowvies.data.database.AppDatabase
import com.appetiser.meowvies.data.database.AppDatabaseManager
import com.appetiser.meowvies.data.model.Movie
import com.appetiser.meowvies.helper.FailCallbackThrowable
import com.appetiser.meowvies.helper.SuccessCallback
import com.appetiser.meowvies.network.APIHandler
import com.appetiser.meowvies.network.APIManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * MainViewModel class to handle the processing/calling of data
 * @constructor application base class for maintaining global application state
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Initialization of the needed objects
     * We use MutableLiveData to observe when new data initialized/added
     */
    var isSuccessfull: MutableLiveData<Boolean> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    var movieList: MutableLiveData<List<Movie.MovieList>> = MutableLiveData()
    var specificGenreList: MutableLiveData<List<Movie.MovieList>> = MutableLiveData()
    var genreList: MutableLiveData<List<String>> = MutableLiveData()

    val allRecent: LiveData<List<Movie.MovieList>>?
    private val appDatabaseManager: AppDatabaseManager

    /**
     * Initialized local database
     * and update allRecent variable (if any)
     */
    init {
        val dao = AppDatabase.getDatabase(application).RecentVisitedMovieDao()
        appDatabaseManager = AppDatabaseManager(dao)
        allRecent = appDatabaseManager.allRecentVisited
    }

    /**
     * In this method, we're calling the encapsulated method from the APIManager to be
     * able to get the data from the stated API
     *
     * @param term URL-encoded text string you want to search
     * @param country two-letter country code
     * @param media type you want to search
     * @param success for the success callback (what to do when it succeed)
     * @param fail for the fail callback (what to do when it fails)
     */
    fun getMovies(
        term: String,
        country: String,
        media: String,
        success: SuccessCallback,
        fail: FailCallbackThrowable
    ) {
        isLoading.value = true
        val getMovieList = APIManager().getMovies(term, country, media).map {
            movieList.postValue(it.movieList)
            getGenres(it)
        }
        APIHandler.sendAPI(getMovieList, {
            isLoading.value = false
            isSuccessfull.value = true
            success.invoke()
        }, {
            isLoading.value = false
            isSuccessfull.value = false
            fail.invoke(it)
        })
    }

    /**
     * This method filters distinctively and sorted out all the genre present in the Movie data class
     * @param movie get the Movie data class
     * @see Movie
     */
    private fun getGenres(movie: Movie) {
        genreList.postValue(
            movie.movieList.map {
                it.genre
            }.distinct().sorted()
        )
    }

    /**
     * This method initializes new list of movies depending on the selected genre
     * @param genre specific genre
     * @param selectedAll return general list if the All Genre was tapped else specific
     */
    fun getSpecificGenre(genre: String, selectedAll: Boolean) {
        if (selectedAll) {
            specificGenreList.postValue(movieList.value)
        } else {
            specificGenreList.postValue(movieList.value?.filter { it.genre == genre })
        }
    }

    /**
     * This method inserts an object to the local database using coroutine
     * @param movie object to save to the local database
     */
    fun insertRecent(movie: Movie.MovieList) {
        GlobalScope.launch {
            appDatabaseManager.saveMovie(movie)
        }
    }

}