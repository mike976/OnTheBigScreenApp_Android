package com.example.onthebigscreen.network

import com.example.onthebigscreen.featured.model.Media
import com.google.gson.annotations.SerializedName

data class MediaApiResponse(
    //val dates: Dates,
    val page: Int, // 1
    val results: List<Media>,
    @SerializedName("total_pages") val totalPages: Int // 44
    //val total_results: Int // 861
)