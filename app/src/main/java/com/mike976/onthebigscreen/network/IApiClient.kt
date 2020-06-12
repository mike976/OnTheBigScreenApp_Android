package com.example.onthebigscreen.network


import com.mike976.onthebigscreen.network.response.MediaCreditsAPIResponse
import com.mike976.onthebigscreen.network.response.MediaDetailApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiClient {

    companion object{

        const val NOW_PLAYING_MOVIES = "movie/now_playing"
        const val UPCOMING_MOVIES = "movie/upcoming"
        const val TRENDING_MOVIES="trending/movie/day"
        const val SEARCH_MOVIES = "search/multi"
        const val TRENDING_TVSHOWS ="trending/tv/day"

        const val TVSHOW = "tv"
        const val MOVIE = "movie"

        const val LANGUAGE_KEY = "language"
        const val LANGUAGE_VALUE = "en-GB"

        const val API_KEY = "api_key"
        const val API_KEY_VALUE = "5cd88ad1ae9b1b0d53d5e8467fe9c9bb"

        const val SORTBY_KEY = "sort_by"
        const val SORTBY_VALUE = "popularity.desc"

        const val QUERY_KEY = "query"

        const val APPEND_TO_RESPONSE_KEY = "append_to_response"
        const val APPEND_TO_RESPONSE_VALUE = "videos"

    }

    @GET(NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(
        @Query(API_KEY)key: String = API_KEY_VALUE,
        @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE,
        @Query(SORTBY_KEY) sortBy:String = SORTBY_VALUE,
        @Query("page") page: Int=1): Call<MediaApiResponse>

    @GET(TRENDING_TVSHOWS)
    fun getTrendingTvShows(@Query(API_KEY)key: String = API_KEY_VALUE,
                           @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE,
                           @Query(SORTBY_KEY) sortBy:String = SORTBY_VALUE,
                           @Query("page") page: Int=1): Call<MediaApiResponse>

    @GET(UPCOMING_MOVIES)
    fun getUpComingMovies(@Query(API_KEY)key: String = API_KEY_VALUE,
                          @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE,
                          @Query(SORTBY_KEY) sortBy:String = SORTBY_VALUE,
                          @Query("page") page: Int=1): Call<MediaApiResponse>

    @GET(TRENDING_MOVIES)
    fun getTrendingMovies(@Query(API_KEY)key: String = API_KEY_VALUE,
                          @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE,
                          @Query(SORTBY_KEY) sortBy:String = SORTBY_VALUE,
                          @Query("page") page: Int=1): Call<MediaApiResponse>

    @GET(SEARCH_MOVIES)
    fun getSearchMovies(@Query(QUERY_KEY) query: String,
                        @Query(API_KEY)key: String = API_KEY_VALUE,
                        @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE): Call<MediaApiResponse>


    @GET("$MOVIE/{id}")
    fun getMovieDetail(@Path("id") id:String,
                        @Query(APPEND_TO_RESPONSE_KEY) appendToResponse:String = APPEND_TO_RESPONSE_VALUE,
                        @Query(API_KEY)key: String = API_KEY_VALUE,
                        @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE) : Call<MediaDetailApiResponse>

    @GET("$TVSHOW/{id}")
    fun getTVShowDetail(@Path("id") id:String,
                        @Query(APPEND_TO_RESPONSE_KEY) appendToResponse:String = APPEND_TO_RESPONSE_VALUE,
                        @Query(API_KEY)key: String = API_KEY_VALUE,
                        @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE) : Call<MediaDetailApiResponse>

    @GET("$MOVIE/{id}/credits")
    fun getMovieCredits(@Path("id") id:String,
                       @Query(API_KEY)key: String = API_KEY_VALUE,
                       @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE) : Call<MediaCreditsAPIResponse>

    @GET("$TVSHOW/{id}/credits")
    fun getTVShowCredits(@Path("id") id:String,
                        @Query(API_KEY)key: String = API_KEY_VALUE,
                        @Query(LANGUAGE_KEY) language:String = LANGUAGE_VALUE) : Call<MediaCreditsAPIResponse>


}

