package com.example.pagging.restApi

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdrop_path: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("genre_ids")
    val genre_ids: List<Int>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_language")
    val original_language: String,

    @SerializedName("original_title")
    val original_title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("title")
    val title: String

)
