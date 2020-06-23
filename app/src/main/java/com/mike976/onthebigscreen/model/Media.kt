package com.mike976.onthebigscreen.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class Media (
    @SerializedName("id") val id: Int = 0,
    @SerializedName("overview") val overview: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("title") var _title: String = "",
    @SerializedName("first_air_date") val firstAirDate: String = "",
    @SerializedName("release_date") val releaseDate: String = "",
    @SerializedName("poster_path") val posterPath: String = "",
    @SerializedName("backdrop_path") val backdropPath: String = "",
    @SerializedName("vote_average") val voteAverage: Float = 0.toFloat(),
    @SerializedName("media_type") val search_by_mediaType: String = "unknown",
    private val _mediaType: MediaType = MediaType.None
) {

    open val mediaType: MediaType
        get() {
            if (search_by_mediaType == "movie") {
                return MediaType.Movie
            } else if (search_by_mediaType == "tv") {
                return MediaType.TVShow
            } else if (!firstAirDate.isNullOrEmpty()) {
                return MediaType.TVShow
            } else if (!releaseDate.isNullOrEmpty()) {
                return MediaType.Movie
            } else {
                return MediaType.None
            }

        }

    open val title: String
        get() {
            if (_title.isEmpty()) {
                return name
            } else if (name.isEmpty()) {
                return _title
            } else {
                return ""
            }
        }

    open val formattedReleaseDate: String
        get() {
            if (search_by_mediaType == "movie") {
                val date = LocalDate.parse(releaseDate)
                return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            } else if (search_by_mediaType == "tv") {
                val date = LocalDate.parse(firstAirDate)
                return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            } else {
                return ""
            }
        }

    open val formattedReleaseYear: String
        get() {
            if (search_by_mediaType == "movie" && !releaseDate.isEmpty()) {
                val date = LocalDate.parse(releaseDate)
                return date.format(DateTimeFormatter.ofPattern("yyyy"))
            } else if (search_by_mediaType == "tv" && !firstAirDate.isEmpty()) {
                val date = LocalDate.parse(firstAirDate)
                return date.format(DateTimeFormatter.ofPattern("yyyy"))
            } else {
                return ""
            }
        }

    val posterUrl : String
        get() {

            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("image.tmdb.org")
                .appendEncodedPath("t")
                .appendEncodedPath("p")
                .appendEncodedPath("w185")
                .appendEncodedPath(posterPath)

            return builder.build().toString()

        }

    val backdropUrl : String
        get() {

            val builder = Uri.Builder()
            builder.scheme("https")
                .authority("image.tmdb.org")
                .appendEncodedPath("t")
                .appendEncodedPath("p")
                .appendEncodedPath("w500")
                .appendEncodedPath(backdropPath)

            return builder.build().toString()

        }
}