package com.appetiser.meowvies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created data class to hold our needed information about a movie/collections of movie
 * @constructor movieList creates a collection of movies
 */
data class Movie (
    @SerializedName("results" )var movieList: List<MovieList>
){
    @Entity(tableName = "movie_list")
    /**
     * @constructor trackId gets the unique ID of the track
     * @constructor genre gets the genre of a certain track
     * @constructor trackName gets the title of a certain track
     * @constructor artwork gets the url of the track's thumbnail, we use the size artworkUrl100 for this
     * @constructor longDescription gets the long description of certain track
     */
    data class MovieList(
        @ColumnInfo(name = "movie_id")
        @PrimaryKey
        @SerializedName("trackId") val trackId: Int,
        @SerializedName("primaryGenreName") val genre: String,
        @SerializedName("trackName") val trackName: String,
        @SerializedName("artworkUrl100") val artwork: String?,
        @SerializedName("trackPrice") val price: Double,
        @SerializedName("longDescription") val longDescription: String,
        var dateVisited: String ? = ""
    )
}