package com.example.moviestreaming.view.home
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.databinding.FragmentHomeBinding
import com.example.moviestreaming.databinding.FragmentProfileBinding
import com.example.moviestreaming.databinding.FragmentSearchBinding

class HomeFragment : BaseFragment() {
    private var _binding : FragmentHomeBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}