package com.mike976.onthebigscreen.view.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.featured.model.MediaType
import com.example.onthebigscreen.network.IApiClient
import com.mike976.onthebigscreen.app.component
import com.mike976.onthebigscreen.model.FeaturedCategory
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
        return MediaDataSource(this.featuredCategory, api)
    }
}