package com.example.onthebigscreen.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.example.onthebigscreen.network.*
import com.mike976.onthebigscreen.network.response.MediaCreditsAPIResponse
import com.mike976.onthebigscreen.network.response.MediaDetailApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDatabaseService {

    private val api: ApiClient

    init {

        val retrofit = providesRetrofit()
        api = retrofit.create(ApiClient::class.java)
    }

    private fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getNowPlayingMovies(): LiveData<ApiResponseMessage<List<Movie>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Movie>>>()

        api.getNowPlayingMovies().enqueue(object : Callback<MoviesApiResponse> {
            override fun onResponse(
                call: Call<MoviesApiResponse>,
                response: Response<MoviesApiResponse>
            ) {
                if (response != null && response.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(response.body()?.results) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.NOW_PLAYING_MOVIES, null)
                }
            }

            override fun onFailure(call: Call<MoviesApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    fun getUpComingMovies(): LiveData<ApiResponseMessage<List<Movie>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Movie>>>()

        api.getUpComingMovies().enqueue(object : Callback<MoviesApiResponse> {
            override fun onResponse(
                call: Call<MoviesApiResponse>,
                response: Response<MoviesApiResponse>
            ) {
                if (response != null && response.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(response.body()?.results) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.UPCOMING_MOVIES, null)
                }
            }

            override fun onFailure(call: Call<MoviesApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }


    fun getTrendingMovies(): LiveData<ApiResponseMessage<List<Movie>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Movie>>>()

        api.getTrendingMovies().enqueue(object : Callback<MoviesApiResponse> {
            override fun onResponse(
                call: Call<MoviesApiResponse>,
                response: Response<MoviesApiResponse>
            ) {
                if (response != null && response.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(response.body()?.results) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.TRENDING_MOVIES, null)
                }
            }

            override fun onFailure(call: Call<MoviesApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    fun getSearchMovies(searchText: String): LiveData<ApiResponseMessage<List<Media>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Media>>>()

        api.getSearchMovies(searchText).enqueue(object : Callback<MediaApiResponse> {
            override fun onResponse(
                call: Call<MediaApiResponse>,
                response: Response<MediaApiResponse>
            ) {
                if (response != null && response.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(response.body()?.results) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.SEARCH_MOVIES, null)
                }
            }

            override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    fun getTrendingTvShows(): LiveData<ApiResponseMessage<List<TvShow>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<TvShow>>>()

        api.getTrendingTvShows().enqueue(object : Callback<TvShowsApiResponse> {
            override fun onResponse(
                call: Call<TvShowsApiResponse>,
                apiResponse: Response<TvShowsApiResponse>
            ) {
                if (apiResponse != null && apiResponse.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(apiResponse.body()?.results) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.TRENDING_TVSHOWS, null)
                }
            }

            override fun onFailure(call: Call<TvShowsApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    fun getMovieDetail(mediaId: Int): LiveData<ApiResponseMessage<MediaDetailApiResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaDetailApiResponse>>()

        api.getMovieDetail(mediaId.toString()).enqueue(object : Callback<MediaDetailApiResponse> {
            override fun onResponse(
                call: Call<MediaDetailApiResponse>,
                apiResponse: Response<MediaDetailApiResponse>
            ) {
                if (apiResponse != null && apiResponse.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(apiResponse.body()) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.MOVIEDETAIL, null)
                }
            }

            override fun onFailure(call: Call<MediaDetailApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    fun getTvShowDetail(mediaId: Int): LiveData<ApiResponseMessage<MediaDetailApiResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaDetailApiResponse>>()

        api.getTVShowDetail(mediaId.toString()).enqueue(object : Callback<MediaDetailApiResponse> {
            override fun onResponse(
                call: Call<MediaDetailApiResponse>,
                apiResponse: Response<MediaDetailApiResponse>
            ) {
                if (apiResponse != null && apiResponse.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(apiResponse.body()) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.TVSHOWDETAIL, null)
                }
            }

            override fun onFailure(call: Call<MediaDetailApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    fun getMovieCredits(mediaId: Int): LiveData<ApiResponseMessage<MediaCreditsAPIResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaCreditsAPIResponse>>()

        api.getMovieCredits(mediaId.toString()).enqueue(object : Callback<MediaCreditsAPIResponse> {
            override fun onResponse(
                call: Call<MediaCreditsAPIResponse>,
                apiResponse: Response<MediaCreditsAPIResponse>
            ) {
                if (apiResponse != null && apiResponse.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(apiResponse.body()) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.MOVIECREDITS, null)
                }
            }

            override fun onFailure(call: Call<MediaCreditsAPIResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    fun getTvShowCredits(mediaId: Int): LiveData<ApiResponseMessage<MediaCreditsAPIResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaCreditsAPIResponse>>()

        api.getTVShowCredits(mediaId.toString()).enqueue(object : Callback<MediaCreditsAPIResponse> {
            override fun onResponse(
                call: Call<MediaCreditsAPIResponse>,
                apiResponse: Response<MediaCreditsAPIResponse>
            ) {
                if (apiResponse != null && apiResponse.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(apiResponse.body()) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.TVSHOWCREDITS, null)
                }
            }

            override fun onFailure(call: Call<MediaCreditsAPIResponse>, t: Throwable) {

            }
        })

        return liveData
    }
}