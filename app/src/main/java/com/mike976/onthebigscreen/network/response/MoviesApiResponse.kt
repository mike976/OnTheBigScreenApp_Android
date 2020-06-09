package com.example.onthebigscreen.network

import com.example.onthebigscreen.featured.model.Movie

data class MoviesApiResponse(
    //val dates: Dates,
    val page: Int, // 1
    val results: List<Movie>
    //val total_pages: Int, // 44
    //val total_results: Int // 861
)

