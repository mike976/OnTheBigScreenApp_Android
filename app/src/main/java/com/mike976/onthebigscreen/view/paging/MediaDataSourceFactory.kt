package com.mike976.onthebigscreen.view.paging

import androidx.paging.DataSource
import com.mike976.onthebigscreen.model.Media
import com.mike976.onthebigscreen.model.MediaType
import com.mike976.onthebigscreen.network.IApiClient
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