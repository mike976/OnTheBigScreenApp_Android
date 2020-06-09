package com.example.onthebigscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.onthebigscreen.featured.model.Movie
import com.example.onthebigscreen.network.ApiError
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.network.ApiResponseStatus
import com.mike976.onthebigscreen.featured.model.MoviesListType
import com.mike976.onthebigscreen.featured.viewmodel.IMainViewModel
import com.mike976.onthebigscreen.featured.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: IMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getMovies(MoviesListType.UpComingMovies)?.observe(this, Observer<ApiResponseMessage<List<Movie>>> { responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val movies = responseMessage.data

                val sb = StringBuilder()
                for (movie in movies) {
                    sb.append("${movie.id} ")
                    sb.append("${movie.title} ")
                    sb.append("${movie.overview} ")
                    sb.append("${movie.formattedReleaseDate} ")
                    sb.append("${movie.formattedReleaseYear} ")
                    sb.append("${movie.posterUrl} ")
                    sb.append("${movie.backdropUrl} ")
                    sb.append("${movie.voteAverage} ")

                    sb.appendln()

                }

                println(sb.toString())

            } else {
                if (responseMessage?.error == ApiError.NOW_PLAYING_MOVIES) {
                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
                }
            }
        })

//        service.getTrendingTvShows().observe(this, Observer<ApiResponseMessage<List<TvShow>>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val trendingTvShows = responseMessage.data
//
//                val sb = StringBuilder()
//                for (movie in trendingTvShows) {
//                    sb.append("${movie.id} ")
//                    sb.append("${movie.title} ")
//                    sb.append("${movie.overview} ")
//                    sb.append("${movie.formattedReleaseDate} ")
//                    sb.append("${movie.formattedReleaseYear} ")
//                    sb.append("${movie.posterUrl} ")
//                    sb.append("${movie.backdropUrl} ")
//                    sb.append("${movie.voteAverage} ")
//
//                    sb.appendln()
//
//                }
//
//                println(sb.toString())
//
//            } else {
//                if (responseMessage?.error == ApiError.TRENDING_TVSHOWS) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getUpComingMovies().observe(this, Observer<ApiResponseMessage<List<Movie>>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val upComingMovies = responseMessage.data
//
//                val sb = StringBuilder()
//                for (movie in upComingMovies) {
//                    sb.append("${movie.id} ")
//                    sb.append("${movie.title} ")
//                    sb.append("${movie.overview} ")
//                    sb.append("${movie.formattedReleaseDate} ")
//                    sb.append("${movie.formattedReleaseYear} ")
//                    sb.append("${movie.posterUrl} ")
//                    sb.append("${movie.backdropUrl} ")
//                    sb.append("${movie.voteAverage} ")
//
//                    sb.appendln()
//
//                }
//
//                println(sb.toString())
//
//            } else {
//                if (responseMessage?.error == ApiError.UPCOMING_MOVIES) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getTrendingMovies().observe(this, Observer<ApiResponseMessage<List<Movie>>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val trendingMoviews = responseMessage.data
//
//                val sb = StringBuilder()
//                for (movie in trendingMoviews) {
//                    sb.append("${movie.id} ")
//                    sb.append("${movie.title} ")
//                    sb.append("${movie.overview} ")
//                    sb.append("${movie.formattedReleaseDate} ")
//                    sb.append("${movie.formattedReleaseYear} ")
//                    sb.append("${movie.posterUrl} ")
//                    sb.append("${movie.backdropUrl} ")
//                    sb.append("${movie.voteAverage} ")
//
//                    sb.appendln()
//
//                }
//
//                println(sb.toString())
//
//            } else {
//                if (responseMessage?.error == ApiError.TRENDING_MOVIES) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getSearchMovies("Star").observe(this, Observer<ApiResponseMessage<List<Media>>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val searchMovies = responseMessage.data
//
//                val sb = StringBuilder()
//                for (movie in searchMovies) {
//                    sb.append("${movie.id} ")
//                    sb.append("${movie.title} ")
//                    sb.append("${movie.overview} ")
//                    sb.append("${movie.formattedReleaseDate} ")
//                    sb.append("${movie.formattedReleaseYear} ")
//                    sb.append("${movie.posterUrl} ")
//                    sb.append("${movie.backdropUrl} ")
//                    sb.append("${movie.voteAverage} ")
//
//                    sb.appendln()
//
//                }
//
//                println(sb.toString())
//
//            } else {
//                if (responseMessage?.error == ApiError.SEARCH_MOVIES) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getMovieDetail(496243).observe(this, Observer<ApiResponseMessage<MediaDetailApiResponse>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val mediaDetailApiResponse = responseMessage.data
//                if (mediaDetailApiResponse != null) {
//
//                    var sb = StringBuilder()
//                    for (pc in mediaDetailApiResponse.productionCompanies) {
//                        sb.append("${pc.name} ")
//                        sb.append("${pc.logoUrl} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                    sb = StringBuilder()
//                    for (pc in mediaDetailApiResponse?.appendedVideos?.results) {
//                        sb.append("${pc.youtubeUrl} ")
//                        sb.append("${pc.youtubeUrlThumbnail} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                }
//
//            } else {
//                if (responseMessage?.error == ApiError.MOVIEDETAIL) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getTvShowDetail(66788).observe(this, Observer<ApiResponseMessage<MediaDetailApiResponse>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val mediaDetailApiResponse = responseMessage.data
//                if (mediaDetailApiResponse != null) {
//
//                    var sb = StringBuilder()
//                    for (pc in mediaDetailApiResponse.productionCompanies) {
//                        sb.append("${pc.name} ")
//                        sb.append("${pc.logoUrl} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                    sb = StringBuilder()
//                    for (pc in mediaDetailApiResponse?.appendedVideos?.results) {
//                        sb.append("${pc.youtubeUrl} ")
//                        sb.append("${pc.youtubeUrlThumbnail} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                }
//
//            } else {
//                if (responseMessage?.error == ApiError.MOVIEDETAIL) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getMovieCredits(496243).observe(this, Observer<ApiResponseMessage<MediaCreditsAPIResponse>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val mediaCreditsApiResponse = responseMessage.data
//                if (mediaCreditsApiResponse != null) {
//
//                    var sb = StringBuilder()
//                    for (pc in mediaCreditsApiResponse.cast) {
//                        sb.append("${pc.name} ")
//                        sb.append("${pc.character} ")
//                        sb.append("${pc.profilePathUrl} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                    sb = StringBuilder()
//                    for (pc in mediaCreditsApiResponse?.crew) {
//                        sb.append("${pc.name} ")
//                        sb.append("${pc.job} ")
//                        sb.append("${pc.profilePathUrl} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                }
//
//            } else {
//                if (responseMessage?.error == ApiError.MOVIECREDITS) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getTvShowCredits(66788).observe(this, Observer<ApiResponseMessage<MediaCreditsAPIResponse>> { responseMessage ->
//
//            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
//                val mediaCreditsApiResponse = responseMessage.data
//                if (mediaCreditsApiResponse != null) {
//
//                    var sb = StringBuilder()
//                    for (pc in mediaCreditsApiResponse.cast) {
//                        sb.append("${pc.name} ")
//                        sb.append("${pc.character} ")
//                        sb.append("${pc.profilePathUrl} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                    sb = StringBuilder()
//                    for (pc in mediaCreditsApiResponse?.crew) {
//                        sb.append("${pc.name} ")
//                        sb.append("${pc.job} ")
//                        sb.append("${pc.profilePathUrl} ")
//
//                        sb.appendln()
//
//                    }
//                    println(sb.toString())
//
//                }
//
//            } else {
//                if (responseMessage?.error == ApiError.MOVIECREDITS) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
    }




}