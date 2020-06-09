package com.mike976.onthebigscreen.network.response

import com.mike976.onthebigscreen.featured.model.Cast
import com.mike976.onthebigscreen.featured.model.Crew

data class MediaCreditsAPIResponse (val cast: List<Cast>, val crew: List<Crew>) {}