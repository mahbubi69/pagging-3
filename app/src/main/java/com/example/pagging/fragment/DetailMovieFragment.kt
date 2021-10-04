package com.example.pagging.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pagging.R
import com.example.pagging.databinding.FragmentDetailMovieBinding
import com.example.pagging.value.ConstValue

class DetailMovieFragment : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDetailBack.setOnClickListener{
            findNavController().navigate(R.id.action_detailMovieFragment_to_movieFragment)
        }
        getDataArgumentMovie()
    }

    //mengambil data yg d teruskan dari movie fragment (argument)
    //menerima
    fun getDataArgumentMovie() {
        val args = arguments?.let { DetailMovieFragmentArgs.fromBundle(it) }
        val id = args?.id
        val tiltle = args?.judul
        val overview = args?.overview
        val img = args?.image

        binding.tvId.text = id.toString()
        binding.tvJudul.text = tiltle
        binding.tvOverview.text = overview
        //image
        Glide.with(this)
            .load(ConstValue.POSTER_PATH + img)
            .into(binding.imgDetail)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}