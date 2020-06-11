package com.mike976.onthebigscreen.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.Media
import com.mike976.onthebigscreen.model.FeaturedCategory
import com.mike976.onthebigscreen.viewmodel.MainViewModel

class MediaListFragment(val featuredCategory: FeaturedCategory) : Fragment() {

    companion object {
        fun newInstance() =
            MediaListFragment(FeaturedCategory.Unknown)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_media_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        viewModel.counter++

        println("Mike ${this.featuredCategory}")


    }

}