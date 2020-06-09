package com.example.onthebigscreen.network

import com.example.onthebigscreen.featured.model.TvShow

data class TvShowsApiResponse(
    val page: Int, // 1
    val results: List<TvShow>
    //val total_pages: Int, // 44
    //val total_results: Int // 861
)
