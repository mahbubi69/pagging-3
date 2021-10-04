package com.example.pagging.viewmodel


import androidx.lifecycle.ViewModel
import com.example.pagging.repo.MovieRepository


class ApiMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
       fun getMovie() = movieRepository.letPagingMovieLiveData()

}