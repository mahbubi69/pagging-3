package com.example.pagging.handler

import com.example.pagging.restApi.ResponseMovie

interface MovieOnClik {
    fun onClikItem(listMovieClik: ResponseMovie)
}