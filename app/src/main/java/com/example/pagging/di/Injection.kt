package com.example.pagging.di

import com.example.pagging.repo.MovieRepository
import com.example.pagging.restApi.RetrofitClient

object Injection {
    //injection adalah tempat apa yang ingin d butuhkan dari suatu kelas
    //variabel penyediaan layanan dari api
    private val providerServive = RetrofitClient.getMovieService()

    //fun menyediakan MovieRepositor
    fun providerRepository(): MovieRepository = MovieRepository.getInstance(providerServive)
}