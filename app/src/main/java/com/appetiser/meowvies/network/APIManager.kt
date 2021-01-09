package com.appetiser.meowvies.network

import com.appetiser.meowvies.data.model.Movie
import io.reactivex.Observable

/**
 * APIManager class is created to separate all the collection of api calls
 */
class APIManager{

    var apiService: APIService = APIClient().getRetrofitInstance()!!.create(APIService::class.java)

    /**
     * @param term URL-encoded text string you want to search
     * @param country two-letter country code
     * @param media type you want to search
     */
    fun getMovies(term: String, country: String, media: String): Observable<Movie> {
        return apiService.getMovies(term, country, media, "")
    }
}