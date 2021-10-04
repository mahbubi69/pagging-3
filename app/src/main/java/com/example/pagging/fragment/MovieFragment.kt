package com.example.pagging.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pagging.adapter.DataMovieAdapter
import com.example.pagging.databinding.FragmentMovieBinding
import com.example.pagging.di.ViewModelFactory
import com.example.pagging.handler.MovieOnClik
import com.example.pagging.restApi.ListMovieResponse
import com.example.pagging.restApi.ResponseMovie
import com.example.pagging.viewmodel.ApiMovieViewModel


class MovieFragment : Fragment(), MovieOnClik {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    //panggil clas adapter
    private lateinit var movieAdapter: DataMovieAdapter
    //memamnggil viewModel
    private val viewModel by lazy {
        //pabrik
        val factory = ViewModelFactory.getInstance()
        ViewModelProvider(this, factory)[ApiMovieViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateData()
        initiateRecyclerView()
    }

    //memulai RV
    private fun initiateRecyclerView() {
        movieAdapter = DataMovieAdapter(this)
        movieAdapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (movieAdapter.itemCount < 1) {
                    print("kosong")
                }
            }
        }
        with(binding.rvListMovie) {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }

    }

    //memulai data
    private fun initiateData() {
        viewModel.getMovie().observe(viewLifecycleOwner, Observer { bobyPaging ->
            movieAdapter.submitData(lifecycle, bobyPaging)
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //item akan pindah
    override fun onClikItem(listMovieClik: ResponseMovie) {
        val id = listMovieClik.id
        val title = listMovieClik.title
        val overview = listMovieClik.overview
        val img = listMovieClik.poster_path

        //next
        val nextDetailMovie = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(
            id, title, overview,img
        )
        view?.findNavController()?.navigate(nextDetailMovie)
    }
}