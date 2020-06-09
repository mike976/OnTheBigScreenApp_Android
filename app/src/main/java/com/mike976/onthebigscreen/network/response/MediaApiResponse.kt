package com.example.onthebigscreen.network

import com.example.onthebigscreen.featured.model.Media

data class MediaApiResponse(
    //val dates: Dates,
    val page: Int, // 1
    val results: List<Media>
    //val total_pages: Int, // 44
    //val total_results: Int // 861
)