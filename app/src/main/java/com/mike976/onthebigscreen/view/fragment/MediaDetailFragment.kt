package com.mike976.onthebigscreen.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onthebigscreen.R
import com.example.onthebigscreen.featured.model.MediaType
import com.mike976.onthebigscreen.viewmodel.MainViewModel

class MediaDetailFragment(val mediaId: Int, val mediaType: MediaType) : Fragment() {

    companion object {
        fun newInstance() =
            MediaDetailFragment(0, MediaType.None)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.media_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        println("Mike ${mediaId} ${mediaType}")

    }

}