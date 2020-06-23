package com.mike976.onthebigscreen.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.onthebigscreen.R
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
    }




}