package com.appetiser.meowvies.network

import com.appetiser.meowvies.data.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * APIService interface will include the needed endpoints
 */
interface APIService{
    @GET("/search")
            /**
             * @param term URL-encoded text string you want to search
             * @param country two-letter country code
             * @param media type you want to search
             * @param all attribute
             */
    fun getMovies(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("all") all: String?
    ): Observable<Movie>
}