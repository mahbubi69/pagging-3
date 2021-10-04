package com.example.pagging.restApi


import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    //npoint
    @GET("movie/top_rated")
    suspend fun getAllDataMovie(): Response<ListMovieResponse>
}