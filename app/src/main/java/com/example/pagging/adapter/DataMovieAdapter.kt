package com.example.pagging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagging.databinding.ItemListMovieBinding
import com.example.pagging.fragment.MovieFragment

import com.example.pagging.restApi.ResponseMovie
import com.example.pagging.value.ConstValue


class DataMovieAdapter(
    private val onClik: MovieFragment
) : PagingDataAdapter<ResponseMovie, DataMovieAdapter.MovieHolder>(
    DIFF_CALLBACK
) {

    inner class MovieHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //mempersatukan antar data dengan item
        fun bind(movieResponse: ResponseMovie) {
            binding.tvOriginTitle.text = movieResponse.title
            binding.tvIdMovie.text = movieResponse.id.toString()
            //menampilkan gambar menggunakan glide
            Glide.with(itemView.context)
                .load("${ConstValue.POSTER_PATH}${movieResponse.poster_path}")
                .into(binding.imgView)

        }
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)

            holder.itemView.setOnClickListener {
                onClik.onClikItem(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ResponseMovie> =
            object : DiffUtil.ItemCallback<ResponseMovie>() {
                override fun areItemsTheSame(
                    oldItem: ResponseMovie,
                    newItem: ResponseMovie
                ): Boolean {
                    return oldItem.id == newItem.id && oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ResponseMovie,
                    newItem: ResponseMovie
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }


}