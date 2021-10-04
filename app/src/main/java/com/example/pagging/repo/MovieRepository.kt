package com.example.pagging.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.pagging.restApi.MovieService
import com.example.pagging.restApi.ResponseMovie


class MovieRepository(
    private val movieService: MovieService
) {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(movieService: MovieService): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(movieService)
            }
    }

    //pengolahan data
    //mengolah data dari sumber paging
    fun letPagingMovieLiveData(): LiveData<PagingData<ResponseMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                PagingMovieFactorySource(movieService)
            }
        ).liveData
    }
}