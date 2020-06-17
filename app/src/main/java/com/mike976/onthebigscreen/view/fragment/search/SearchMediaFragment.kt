package com.mike976.onthebigscreen.view.fragment.search

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onthebigscreen.R
import com.example.onthebigscreen.network.ApiError
import com.example.onthebigscreen.network.ApiResponseStatus
import com.mike976.onthebigscreen.util.ItemHorizontalDecoration
import com.mike976.onthebigscreen.view.adapter.search.SearchMediaAdapter
import com.mike976.onthebigscreen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_search_media.*


class SearchMediaFragment : Fragment() {

    private val adapter =
        SearchMediaAdapter(
            mutableListOf()
        )

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

        this.hideActionBar()

        searchInfoTextView.visibility = View.VISIBLE

        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!

        activity?.let {
            val dividerWidthInPixels = resources.getDimensionPixelSize(R.dimen.item_divider_height)
            mediaSearchResultRecyclerView.addItemDecoration(ItemHorizontalDecoration(ContextCompat.getColor(it, R.color.colorAccent), dividerWidthInPixels))

        }

        mediaSearchResultRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mediaSearchResultRecyclerView.adapter = adapter


        searchEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(s != null) {
                    updateSearchResults(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        searchEditText.setOnEditorActionListener { v, actionId, event ->

            val x  = when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {

                    updateSearchResults(searchEditText.text?.toString())

                    true
                }
                else -> false
            }

            activity?.let { hideKeyboard(it) }

           return@setOnEditorActionListener(x)
        }
    }

    fun updateSearchResults(searchText: String?) {

        if(searchText.isNullOrEmpty()) {
            adapter.updateSearchResults(emptyList())
            searchInfoTextView.visibility = View.VISIBLE
            return
        }

        searchInfoTextView.visibility = View.GONE

        viewModel.getSearchMovies(searchText).observe(this, Observer {responseMessage ->

            if(responseMessage?.statusApi == ApiResponseStatus.SUCCESS && responseMessage.data != null) {
                val searchResults = responseMessage.data
                println("Mike " + searchResults.size)

                if(searchResults != null) {
                    adapter.updateSearchResults(searchResults)
                }

            } else {
                if (responseMessage?.error == ApiError.SEARCH_MOVIES) {
                    Toast.makeText(context, "No results found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        this.hideActionBar()
        super.onDestroyView()
    }

    fun hideActionBar() {
        try {
            val activity = view?.context as AppCompatActivity
            activity?.supportActionBar!!.hide()
        } catch (e: Exception) {
        }
    }
}
