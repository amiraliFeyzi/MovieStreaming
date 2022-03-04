package com.example.moviestreaming.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.databinding.FragmentSearchBinding


class SearchFragment : BaseFragment() {
    private var _binding:FragmentSearchBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}