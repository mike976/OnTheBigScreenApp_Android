package com.mike976.onthebigscreen.network.response

import com.google.gson.annotations.SerializedName
import com.mike976.onthebigscreen.model.ProductionCompany

data class MediaDetailApiResponse (
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany>,
    @SerializedName("videos") val appendedVideos: AppendedVideosAPIResponse
) {
}

