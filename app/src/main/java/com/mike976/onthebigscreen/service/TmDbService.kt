package com.mike976.onthebigscreen.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.model.Movie
import com.mike976.onthebigscreen.model.TvShow
import com.example.onthebigscreen.network.*
import com.example.onthebigscreen.service.ITmDbService
import com.mike976.onthebigscreen.network.IApiClient
import com.mike976.onthebigscreen.network.response.MediaApiResponse
import com.mike976.onthebigscreen.network.response.MediaCreditsAPIResponse
import com.mike976.onthebigscreen.network.response.MediaDetailApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class TmDbService @Inject constructor (private val api: IApiClient): ITmDbService {

    //TODO REFACTOR

    override fun getNowPlayingMovies(page: Int): LiveData<ApiResponseMessage<List<Movie>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Movie>>>()

        api.getNowPlayingMovies(page=page).enqueue(object : Callback<MediaApiResponse> {
            override fun onResponse(
                call: Call<MediaApiResponse>,
                response: Response<MediaApiResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(response.body()?.results as List<Movie>) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.NOW_PLAYING_MOVIES, null)
                }
            }

            override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    override fun getUpComingMovies(page: Int): LiveData<ApiResponseMessage<List<Movie>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Movie>>>()

        api.getUpComingMovies(page=page).enqueue(object : Callback<MediaApiResponse> {
            override fun onResponse(
                call: Call<MediaApiResponse>,
                response: Response<MediaApiResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(response.body()?.results as List<Movie>) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.UPCOMING_MOVIES, null)
                }
            }

            override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }


    override fun getTrendingMovies(page: Int): LiveData<ApiResponseMessage<List<Movie>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Movie>>>()

        api.getTrendingMovies(page=page).enqueue(object : Callback<MediaApiResponse> {
            override fun onResponse(
                call: Call<MediaApiResponse>,
                response: Response<MediaApiResponse>
            ) {
                if (response.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(response.body()?.results as List<Movie>) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.TRENDING_MOVIES, null)
                }
            }

            override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    override fun getSearchMovies(searchText: String): LiveData<ApiResponseMessage<List<Media>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<Media>>>()

        api.getSearchMovies(searchText).enqueue(object : Callback<MediaApiResponse> {
            override fun onResponse(
                call: Call<MediaApiResponse>,
                response: Response<MediaApiResponse>
            ) {
                if (response.isSuccessful) {
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

    override fun getTrendingTvShows(page:Int): LiveData<ApiResponseMessage<List<TvShow>>> {
        val liveData = MutableLiveData<ApiResponseMessage<List<TvShow>>>()

        api.getTrendingTvShows(page=page).enqueue(object : Callback<MediaApiResponse> {
            override fun onResponse(
                call: Call<MediaApiResponse>,
                apiResponse: Response<MediaApiResponse>
            ) {
                if (apiResponse.isSuccessful) {
                    liveData.value =
                        ApiResponseMessage.success(apiResponse.body()?.results as List<TvShow>) //retrofit is able to convert response body to gists because the data class uses serializedname atribute to ensure can desrialize josn to data class
                } else {
                    liveData.value = ApiResponseMessage.error(ApiError.TRENDING_TVSHOWS, null)
                }
            }

            override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

            }
        })

        return liveData
    }

    override fun getMovieDetail(mediaId: Int): LiveData<ApiResponseMessage<MediaDetailApiResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaDetailApiResponse>>()

        api.getMovieDetail(mediaId.toString()).enqueue(object : Callback<MediaDetailApiResponse> {
            override fun onResponse(
                call: Call<MediaDetailApiResponse>,
                apiResponse: Response<MediaDetailApiResponse>
            ) {
                if (apiResponse.isSuccessful) {
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

    override fun getTvShowDetail(mediaId: Int): LiveData<ApiResponseMessage<MediaDetailApiResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaDetailApiResponse>>()

        api.getTVShowDetail(mediaId.toString()).enqueue(object : Callback<MediaDetailApiResponse> {
            override fun onResponse(
                call: Call<MediaDetailApiResponse>,
                apiResponse: Response<MediaDetailApiResponse>
            ) {
                if (apiResponse.isSuccessful) {
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

    override fun getMovieCredits(mediaId: Int): LiveData<ApiResponseMessage<MediaCreditsAPIResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaCreditsAPIResponse>>()

        api.getMovieCredits(mediaId.toString()).enqueue(object : Callback<MediaCreditsAPIResponse> {
            override fun onResponse(
                call: Call<MediaCreditsAPIResponse>,
                apiResponse: Response<MediaCreditsAPIResponse>
            ) {
                if (apiResponse.isSuccessful) {
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

    override fun getTvShowCredits(mediaId: Int): LiveData<ApiResponseMessage<MediaCreditsAPIResponse>> {
        val liveData = MutableLiveData<ApiResponseMessage<MediaCreditsAPIResponse>>()

        api.getTVShowCredits(mediaId.toString()).enqueue(object : Callback<MediaCreditsAPIResponse> {
            override fun onResponse(
                call: Call<MediaCreditsAPIResponse>,
                apiResponse: Response<MediaCreditsAPIResponse>
            ) {
                if (apiResponse.isSuccessful) {
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