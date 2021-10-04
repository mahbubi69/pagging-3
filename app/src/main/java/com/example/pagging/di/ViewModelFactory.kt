package com.example.pagging.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pagging.repo.MovieRepository
import com.example.pagging.viewmodel.ApiMovieViewModel

class ViewModelFactory(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        //instance (contoh)
        private val instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory = (instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.providerRepository()
            )
        })

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ApiMovieViewModel::class.java) -> {
                ApiMovieViewModel(movieRepository) as T
            }
            else -> throw Throwable("tidak ada view model class" + modelClass.name)
        }
    }
}