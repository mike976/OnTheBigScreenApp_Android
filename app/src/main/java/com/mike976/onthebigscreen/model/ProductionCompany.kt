package com.mike976.onthebigscreen.model

import com.google.gson.annotations.SerializedName

data class ProductionCompany(val name: String, @SerializedName("logo_path") val logoPath: String?) {

    val logoUrl: String
        get() {
            if(logoPath != null) {
                return "https://image.tmdb.org/t/p/w500$logoPath"
            }

            return ""
        }
}

