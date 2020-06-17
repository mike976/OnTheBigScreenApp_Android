package com.mike976.onthebigscreen.viewmodel

import androidx.lifecycle.LiveData
import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.model.MediaType
import com.mike976.onthebigscreen.model.Movie
import com.mike976.onthebigscreen.model.TvShow
import com.example.onthebigscreen.network.ApiResponseMessage
import com.mike976.onthebigscreen.model.MoviesListType
import com.mike976.onthebigscreen.network.response.MediaCreditsAPIResponse
import com.mike976.onthebigscreen.network.response.MediaDetailApiResponse

interface IMainViewModel {

    fun getMovies(moviesListType: MoviesListType) : LiveData<ApiResponseMessage<List<Movie>>>?
    fun getSearchMovies(searchText: String): LiveData<ApiResponseMessage<List<Media>>>
    fun getTrendingTvShows(): LiveData<ApiResponseMessage<List<TvShow>>>
    fun getMediaDetail(mediaId: Int, mediaType: MediaType = MediaType.None): LiveData<ApiResponseMessage<MediaDetailApiResponse>>?
    fun getMediaCredits(mediaId: Int, mediaType: MediaType = MediaType.None): LiveData<ApiResponseMessage<MediaCreditsAPIResponse>>?

}