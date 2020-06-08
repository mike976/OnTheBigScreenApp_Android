package com.example.onthebigscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.onthebigscreen.featured.model.Media
import com.example.onthebigscreen.network.ApiError
import com.example.onthebigscreen.network.ApiResponseMessage
import com.example.onthebigscreen.network.ApiResponseStatus
import com.example.onthebigscreen.service.TheMovieDatabaseService

class MainActivity : AppCompatActivity() {

    private lateinit var service: TheMovieDatabaseService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = TheMovieDatabaseService()

//        service.getNowPlayingMovies().observe(this, Observer<ResponseMessage<List<Movie>>> { responseMessage ->
//
//            if(responseMessage?.status == ResponseStatus.SUCCESS && responseMessage.data != null) {
//                val nowPlayingMovies = responseMessage.data
//
//                val sb = StringBuilder()
//                for (movie in nowPlayingMovies) {
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
//                if (responseMessage?.error == ApiError.NOW_PLAYING_MOVIES) {
//                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

//        service.getTrendingTvShows().observe(this, Observer<ResponseMessage<List<TvShow>>> { responseMessage ->
//
//            if(responseMessage?.status == ResponseStatus.SUCCESS && responseMessage.data != null) {
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

//        service.getUpComingMovies().observe(this, Observer<ResponseMessage<List<Movie>>> { responseMessage ->
//
//            if(responseMessage?.status == ResponseStatus.SUCCESS && responseMessage.data != null) {
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

//        service.getTrendingMovies().observe(this, Observer<ResponseMessage<List<Movie>>> { responseMessage ->
//
//            if(responseMessage?.status == ResponseStatus.SUCCESS && responseMessage.data != null) {
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

        service.getSearchMovies("Star").observe(this, Observer<ApiResponseMessage<List<Media>>> { responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val searchMovies = responseMessage.data

                val sb = StringBuilder()
                for (movie in searchMovies) {
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
                if (responseMessage?.error == ApiError.SEARCH_MOVIES) {
                    Toast.makeText(this, "Now Playing movies not found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }




}