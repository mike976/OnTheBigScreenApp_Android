package com.mike976.onthebigscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onthebigscreen.service.ITmDbService
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor (private val tmDbService: ITmDbService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(tmDbService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}