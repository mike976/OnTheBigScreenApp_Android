package com.example.onthebigscreen.network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    companion object{

        const val NOW_PLAYING_MOVIES = "movie/now_playing"
        const val UPCOMING_MOVIES = "movie/upcoming"
        const val TRENDING_MOVIES="trending/movie/day"
        const val SEARCH_MOVIES = "search/multi"
        const val TRENDING_TVSHOWS ="trending/tv/day"
    }

    @GET(NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(@Query("api_key")key: String = "5cd88ad1ae9b1b0d53d5e8467fe9c9bb"): Call<MoviesApiResponse>

    @GET(TRENDING_TVSHOWS)
    fun getTrendingTvShows(@Query("api_key")key: String = "5cd88ad1ae9b1b0d53d5e8467fe9c9bb"): Call<TvShowsApiResponse>

    @GET(UPCOMING_MOVIES)
    fun getUpComingMovies(@Query("api_key")key: String = "5cd88ad1ae9b1b0d53d5e8467fe9c9bb"): Call<MoviesApiResponse>

    @GET(TRENDING_MOVIES)
    fun getTrendingMovies(@Query("api_key")key: String = "5cd88ad1ae9b1b0d53d5e8467fe9c9bb"): Call<MoviesApiResponse>

    @GET(SEARCH_MOVIES)
    fun getSearchMovies(@Query("query") query: String,
        @Query("api_key")key: String = "5cd88ad1ae9b1b0d53d5e8467fe9c9bb"
                        ): Call<MediaApiResponse>


}

