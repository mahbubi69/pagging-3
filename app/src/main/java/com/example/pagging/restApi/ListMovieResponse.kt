package com.example.pagging.restApi

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val result: List<ResponseMovie>
)
