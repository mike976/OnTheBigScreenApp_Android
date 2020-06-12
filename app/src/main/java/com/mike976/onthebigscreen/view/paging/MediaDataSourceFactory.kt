package com.mike976.onthebigscreen.view.paging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.MediaType
import com.example.onthebigscreen.network.IApiClient
import com.example.onthebigscreen.network.MediaApiResponse
import com.mike976.onthebigscreen.app.component
import com.mike976.onthebigscreen.model.FeaturedCategory
import retrofit2.Call
import javax.inject.Inject


class MediaDataSourceFactory : DataSource.Factory<Int, Media>() {

    @Inject
    lateinit var api: IApiClient

    var mediaType: MediaType = MediaType.None

    var featuredCategory: FeaturedCategory = FeaturedCategory.Unknown

    init {
        component.inject(this)
    }


    override fun create(): DataSource<Int, Media> {

        val dataSourceFetchRequest = getDataSourceFetchRequest()

        val pagingDataSource: PageKeyedDataSource<Int, Media>

//        if(this.mediaType == MediaType.TVShow) {
//            pagingDataSource = TvShowDataSource()
//        } else {
            pagingDataSource = MovieDataSource(dataSourceFetchRequest)
        //}

        return pagingDataSource

    }

    private fun getDataSourceFetchRequest(page: Int = 1) : (Int) -> Call<MediaApiResponse> {
        return {
            when (this.featuredCategory) {
                FeaturedCategory.NowPlaying -> {
                    api.getNowPlayingMovies(page = page)
                }
                FeaturedCategory.TrendingMovies -> {
                    api.getTrendingMovies(page = page)
                }
                FeaturedCategory.Upcoming -> {
                    api.getUpComingMovies(page = page)
                }
                else -> {
                    api.getTrendingTvShows(page = page)
                }
            }
        }
    }

}