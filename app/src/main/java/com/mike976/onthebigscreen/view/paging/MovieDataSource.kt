package com.mike976.onthebigscreen.view.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PageKeyedDataSource
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.network.ApiError
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.network.MediaApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource(val apiCall : (Int) -> Call<MediaApiResponse>)
    : PageKeyedDataSource<Int, Media>(), IPagedDataSource {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Media>
    ) {

        apiCall(IPagedDataSource.FIRST_PAGE)
            .enqueue(object: Callback<MediaApiResponse> {

                override fun onResponse(
                    call: Call<MediaApiResponse>,
                    response: Response<MediaApiResponse>
                ) {
                    if (response != null && response.isSuccessful) {
                        callback.onResult(response.body()!!.results, null, IPagedDataSource.FIRST_PAGE + 1)

                    }
                }

                override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

                }

            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Media>) {

        apiCall(params.key)
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

        apiCall(params.key)
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
                    }
                }

                override fun onFailure(call: Call<MediaApiResponse>, t: Throwable) {

                }

            })

    }


}