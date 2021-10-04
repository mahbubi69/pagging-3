package com.example.pagging.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagging.restApi.MovieService
import com.example.pagging.restApi.ResponseMovie
import com.example.pagging.value.ConstValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


//sumber dari pagging data
class PagingMovieFactorySource(
    private val movieService: MovieService
) :
//paging data menggunakan 2 parameter <key,value(data yg akan d ambil)>
    PagingSource<Int, ResponseMovie>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseMovie>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseMovie> {
        //memulai halaman
        val pageNumber = params.key ?: ConstValue.FIRST_PAGE
        return try {
            withContext(Dispatchers.IO) {
                //melayani
                val service = movieService.getAllDataMovie()
                //tanggapan dari pelayanan
                val response = service.body()
                //trus ngedata hasil dari respon
                val data = response?.result

                LoadResult.Page(
                    data = data.orEmpty(),
                    //jika nomor page lebih kecil dari tanggapan maka akan d kurangi 1
                    prevKey = if (pageNumber < response?.page!!) null else pageNumber - 1,
                    //jika nomor page lebih besar dari tanggapan maka akan d tambah 1
                    nextKey = if (pageNumber > response.page) null else pageNumber + 1
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}