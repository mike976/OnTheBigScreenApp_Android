package com.example.onthebigscreen.featured.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Movie : Media() {

    override val mediaType: MediaType
        get() = MediaType.Movie

    override val title: String
        get() {
            return _title
        }

    override val formattedReleaseDate: String
        get() {

            val date = LocalDate.parse(releaseDate)
            return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        }

    override val formattedReleaseYear: String
        get() {
            val date = LocalDate.parse(releaseDate)
            return date.format(DateTimeFormatter.ofPattern("yyyy"))
        }
}