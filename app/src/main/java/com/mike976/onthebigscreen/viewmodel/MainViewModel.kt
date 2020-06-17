package com.mike976.onthebigscreen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.model.MediaType
import com.mike976.onthebigscreen.model.Movie
import com.mike976.onthebigscreen.model.TvShow
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.service.ITmDbService
import com.mike976.onthebigscreen.app.component
import com.mike976.onthebigscreen.model.FeaturedCategory
import com.mike976.onthebigscreen.model.MoviesListType
import com.mike976.onthebigscreen.network.response.MediaCreditsAPIResponse
import com.mike976.onthebigscreen.network.response.MediaDetailApiResponse
import com.mike976.onthebigscreen.view.paging.IPagedDataSource
import com.mike976.onthebigscreen.view.paging.MediaDataSourceFactory


class MainViewModel (private val service: ITmDbService) : ViewModel(), IMainViewModel {

    var nowPlayingMoviesPagedList: LiveData<PagedList<Media>>
    var upComingMoviesPagedList: LiveData<PagedList<Media>>
    var trendingMoviesPagedList: LiveData<PagedList<Media>>
    var trendingTvShowsPagedList: LiveData<PagedList<Media>>

    var counter = 0

    init {
        component.inject(this)

        nowPlayingMoviesPagedList = createPagedList(MediaType.Movie, FeaturedCategory.NowPlaying)
        upComingMoviesPagedList = createPagedList(MediaType.Movie, FeaturedCategory.Upcoming)
        trendingMoviesPagedList = createPagedList(MediaType.Movie, FeaturedCategory.TrendingMovies)
        trendingTvShowsPagedList = createPagedList(MediaType.TVShow, FeaturedCategory.TrendingTv)

    }


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

    private fun createPagedList (mediaType: MediaType, featuredCategory: FeaturedCategory): LiveData<PagedList<Media>> {

        val factory = MediaDataSourceFactory()
        factory.featuredCategory = featuredCategory
        factory.mediaType = mediaType

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(IPagedDataSource.PAGE_SIZE)
            .build()

        return LivePagedListBuilder(factory, config).build()
    }


}

