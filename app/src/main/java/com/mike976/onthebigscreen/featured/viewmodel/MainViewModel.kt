package com.mike976.onthebigscreen.featured.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.MediaType
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.featured.model.TvShow
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.service.ITmDbService
import com.example.onthebigscreen.service.TmDbService
import com.mike976.onthebigscreen.featured.model.MoviesListType
import com.mike976.onthebigscreen.network.response.MediaCreditsAPIResponse
import com.mike976.onthebigscreen.network.response.MediaDetailApiResponse

class MainViewModel : ViewModel(), IMainViewModel {

    private val service: ITmDbService = TmDbService()

    override fun getMovies(moviesListType: MoviesListType) : LiveData<ApiResponseMessage<List<Movie>>>? {

        return when(moviesListType) {
            MoviesListType.NowPlayingMovies -> service.getNowPlayingMovies()
            MoviesListType.TrendingMovies -> service.getTrendingMovies()
            MoviesListType.UpComingMovies -> service.getUpComingMovies()
            else -> null
        }
    }

    override fun getSearchMovies(searchText: String): LiveData<ApiResponseMessage<List<Media>>> {
        return service.getSearchMovies(searchText)
    }

    override fun getTrendingTvShows(): LiveData<ApiResponseMessage<List<TvShow>>> {
        return service.getTrendingTvShows()
    }

    override fun getMediaDetail(mediaId: Int, mediaType: MediaType): LiveData<ApiResponseMessage<MediaDetailApiResponse>>? {

        if (mediaType == MediaType.TVShow) {
            return service.getTvShowDetail(mediaId)
        } else if (mediaType == MediaType.Movie) {
            return service.getMovieDetail(mediaId)
        }

        return null
    }

    override fun getMediaCredits(mediaId: Int, mediaType: MediaType) : LiveData<ApiResponseMessage<MediaCreditsAPIResponse>>? {

        if (mediaType == MediaType.TVShow) {
            return service.getTvShowCredits(mediaId)
        } else if (mediaType == MediaType.Movie) {
            return service.getMovieCredits(mediaId)
        }

        return null
    }


}

