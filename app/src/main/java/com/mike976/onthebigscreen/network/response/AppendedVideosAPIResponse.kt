package com.mike976.onthebigscreen.network.response

import com.mike976.onthebigscreen.featured.model.Trailer

data class AppendedVideosAPIResponse (
    val results: List<Trailer>
)