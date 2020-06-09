package com.example.onthebigscreen.service

import androidx.lifecycle.LiveData
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.example.onthebigscreen.network.ApiResponseMessage
import com.mike976.onthebigscreen.network.response.MediaCreditsAPIResponse
import com.mike976.onthebigscreen.network.response.MediaDetailApiResponse

interface ITmDbService {

    fun getNowPlayingMovies(): LiveData<ApiResponseMessage<List<Movie>>>
    fun getUpComingMovies(): LiveData<ApiResponseMessage<List<Movie>>>
    fun getTrendingMovies(): LiveData<ApiResponseMessage<List<Movie>>>
    fun getSearchMovies(searchText: String): LiveData<ApiResponseMessage<List<Media>>>
    fun getTrendingTvShows(): LiveData<ApiResponseMessage<List<TvShow>>>
    fun getMovieDetail(mediaId: Int): LiveData<ApiResponseMessage<MediaDetailApiResponse>>
    fun getTvShowDetail(mediaId: Int): LiveData<ApiResponseMessage<MediaDetailApiResponse>>
    fun getMovieCredits(mediaId: Int): LiveData<ApiResponseMessage<MediaCreditsAPIResponse>>
    fun getTvShowCredits(mediaId: Int): LiveData<ApiResponseMessage<MediaCreditsAPIResponse>>

}