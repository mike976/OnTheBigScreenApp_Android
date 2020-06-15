package com.example.onthebigscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mike976.onthebigscreen.app.component
import com.mike976.onthebigscreen.view.fragment.about.AboutFragment
import com.mike976.onthebigscreen.view.fragment.featured.FeaturedMediaFragment
import com.mike976.onthebigscreen.view.fragment.search.SearchMediaFragment
import com.mike976.onthebigscreen.viewmodel.MainViewModel
import com.mike976.onthebigscreen.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    private val featuredFragment =
        FeaturedMediaFragment()
    private val searchFragment =
        SearchMediaFragment()
    private val aboutFragment =
        AboutFragment()



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment = when (item.itemId) {
            R.id.navigation_featured -> featuredFragment
            R.id.navigation_search -> searchFragment
            R.id.navigation_about -> aboutFragment
            else -> FeaturedMediaFragment()
        }
        switchToFragment(fragment)
        true
    }

    private fun switchToFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment).commit()
    }

    fun hideActionBar() {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideActionBar()

        component.inject(this)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        switchToFragment(featuredFragment)


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