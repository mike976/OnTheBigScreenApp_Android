package com.mike976.onthebigscreen.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.viewmodel.MainViewModel

class SearchMediaFragment : Fragment() {

    companion object {
        fun newInstance() =
            SearchMediaFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_media, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        viewModel.counter++
    }

}