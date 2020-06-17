package com.mike976.onthebigscreen.view.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PageKeyedDataSource
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.network.ApiError
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.network.IApiClient
import com.example.onthebigscreen.network.MediaApiResponse
import com.mike976.onthebigscreen.model.FeaturedCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaDataSource(private val featuredCategory: FeaturedCategory, private val api: IApiClient)
    : PageKeyedDataSource<Int, Media>(), IPagedDataSource {

    fun apiCall(page: Int) :  Call<MediaApiResponse> {
        return when (featuredCategory) {
                FeaturedCategory.NowPlaying ->
                    api.getNowPlayingMovies(page = page)

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


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Media>
    ) {

        apiCall(page=IPagedDataSource.FIRST_PAGE)
            .enqueue(object: Callback<MediaApiResponse> {

                override fun onResponse(
                    call: Call<MediaApiResponse>,
                    response: Response<MediaApiResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onResult(response.body()!!.results, null, IPagedDataSource.FIRST_PAGE + 1)

                    }
                }

                override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

                }

            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Media>) {

        apiCall(page=params.key)
            .enqueue(object: Callback<MediaApiResponse> {

                override fun onResponse(
                    call: Call<MediaApiResponse>,
                    response: Response<MediaApiResponse>
                ) {
                    response.body()?.results?.let {

                        var key: Int? = null
                        if(params.key > 1) {
                            key = (params.key - 1)
                        }

                        callback.onResult(it,  key)
                    }
                }

                override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Media>) {


        apiCall(page=params.key)
                    .enqueue(object: Callback<MediaApiResponse> {

                override fun onResponse(
                    call: Call<MediaApiResponse>,
                    response: Response<MediaApiResponse>
                ) {
                    response.body()?.let {

                        var key: Int? = null
                        if(it.totalPages > params.key) {
                            key = (params.key + 1)
                        }

                        callback.onResult(it.results, key)
                        println("After ${it.results[0].title}" )
                    }
                }

                override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

                }

            })

    }

}