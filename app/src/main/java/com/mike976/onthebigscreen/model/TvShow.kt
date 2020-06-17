package com.mike976.onthebigscreen.model

import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.model.MediaType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TvShow : Media() {

    override val mediaType: MediaType
        get() = MediaType.TVShow

    override val title: String
        get() {
            return name
        }

    override val formattedReleaseDate: String
        get() {
            val date = LocalDate.parse(firstAirDate)
            return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        }

    override val formattedReleaseYear: String
        get() {
            val date = LocalDate.parse(firstAirDate)
            return date.format(DateTimeFormatter.ofPattern("yyyy"))

        }
}