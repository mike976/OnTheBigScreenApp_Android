package com.mike976.onthebigscreen.featured.model

import com.google.gson.annotations.SerializedName

data class Crew (val name: String, val job: String, @SerializedName("profile_path") val profilePath:String?) {

    val profilePathUrl : String
        get() {
            if (profilePath != null) {
                return "https://image.tmdb.org/t/p/w500$profilePath"
            }

            return ""
        }
}

