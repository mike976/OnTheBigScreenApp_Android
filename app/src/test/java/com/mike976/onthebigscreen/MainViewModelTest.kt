package com.mike976.onthebigscreen

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.network.ApiResponseStatus
import com.example.onthebigscreen.service.ITmDbService
import com.mike976.onthebigscreen.model.*
import com.mike976.onthebigscreen.view.paging.MediaDataSourceFactory
import com.mike976.onthebigscreen.viewmodel.MainViewModel
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject


class MainViewModelTest {

    @Mock
    private var _tmDbService: ITmDbService = Mockito.mock(ITmDbService::class.java)

    @Mock
    lateinit var mediaDataSourceFactory: MediaDataSourceFactory

    lateinit var _sut: MainViewModel

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        _tmDbService = Mockito.mock(ITmDbService::class.java)

        Mockito.`when`(mediaDataSourceFactory.featuredCategory).thenReturn(FeaturedCategory.NowPlaying)
        Mockito.`when`(mediaDataSourceFactory.mediaType).thenReturn(MediaType.Movie)

        _sut = MainViewModel(_tmDbService)


    }

    @Test
    fun shouldReturnNowPlayingMovies() {

        //ARRANGE
        var movie = Movie()
        movie._title = "Movie title"

        val moviesList: MutableList<Movie> = mutableListOf()
        moviesList.add(movie)

        val apiResponse = ApiResponseMessage(ApiResponseStatus.SUCCESS, moviesList, null)
        val liveData = MutableLiveData<ApiResponseMessage<List<Movie>>>()
        liveData.value = apiResponse

        Mockito.`when`(_tmDbService.getNowPlayingMovies()).thenReturn(liveData)

        //ACT
        _sut.getMovies(MoviesListType.NowPlayingMovies)?.observeForever {

            val movieList = it.data
            val count = movieList?.size
            val title = movieList?.first()?.title

            Assert.assertTrue(movieList?.size == 1)
            Assert.assertTrue(movieList?.first()?.title == movie.title)
        }

        Mockito.verify(_tmDbService).getNowPlayingMovies()
    }

    @After
    fun tearDown() {

    }
}
