package com.example.moviestreaming.view.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.databinding.FragmentGenreBinding

class GenreFragment : BaseFragment() {
    private var _binding: FragmentGenreBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGenreBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}