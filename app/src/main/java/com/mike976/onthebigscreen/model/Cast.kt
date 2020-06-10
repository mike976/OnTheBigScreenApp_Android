package com.mike976.onthebigscreen.model

import com.google.gson.annotations.SerializedName

data class Cast (val name: String, val character: String, @SerializedName("profile_path") val profilePath:String?) {

    val profilePathUrl : String
        get() {
            if (profilePath != null) {
                return "https://image.tmdb.org/t/p/w500$profilePath"
            }

            return ""
        }
}