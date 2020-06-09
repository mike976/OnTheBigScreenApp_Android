package com.mike976.onthebigscreen.featured.model

data class Trailer(val key:String?) {

    val youtubeUrl: String
        get() {
            if(key != null) {
                return "https://www.youtube.com/watch?v=$key"
            }

            return ""
        }

    val youtubeUrlThumbnail: String
        get() {
            if(key != null) {
                return "https://img.youtube.com/vi/$key/0.jpg"
            }

            return ""
        }
}

