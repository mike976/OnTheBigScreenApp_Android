package com.mike976.onthebigscreen.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_featured.*

class FeaturedFragment : Fragment() {

    companion object {
        fun newInstance() = FeaturedFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val mediaListFragment =
        MediaListFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_featured, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Note the app has one activity - MainActivity - so we pull out the MainViewModel.
        //To share the same instance of MainViewModel across fragments, we're using the mainactivity to provide us the viewmodel
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        println(viewModel.counter) //same instance viewmodel checker

        mediaListButton.setOnClickListener {

            switchToFragment(mediaListFragment)
        }


    }

    private fun switchToFragment(fragment: Fragment) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragment.javaClass.name)
            .commit()

    }



}