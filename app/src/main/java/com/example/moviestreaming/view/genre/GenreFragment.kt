package com.example.moviestreaming.view.genre

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.FragmentGenreBinding
import com.example.moviestreaming.model.dataclass.Genre
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import com.example.moviestreaming.view.genremovie.GenreMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GenreFragment : BaseFragment(),GenreAdapter.OnGenreClickListener {
    private var _binding: FragmentGenreBinding?=null
    private val binding get() = _binding!!

    private val viewModel:GenreViewModel by viewModels()

    @Inject
    lateinit var imageLoading:ImageLoading

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGenreBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData(){
        viewModel.genreListLiveData.observe(viewLifecycleOwner){
            setUpGenreList(it)
        }
    }

    private fun setUpGenreList(genres:List<Genre>){
        val genreAdapter = GenreAdapter(imageLoading , this)
        genreAdapter.setData(genres)
        binding.rvGenre.layoutManager = GridLayoutManager(requireContext() , 2)
        binding.rvGenre.adapter = genreAdapter

        //Hide Back Btn on toolbar
        binding.toolbar.showBackBtn = false
        binding.toolbar.checkBackBtnShow()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onGenreClick(genre: Genre) {
        startActivity(Intent(requireContext() , GenreMovieActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , genre.name)
        })
    }


}