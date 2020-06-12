package com.mike976.onthebigscreen.view.paging

import androidx.paging.PageKeyedDataSource
import com.example.onthebigscreen.featured.model.Media

class TvShowDataSource() : PageKeyedDataSource<Int, Media>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Media>
    ) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Media>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Media>) {

    }
}