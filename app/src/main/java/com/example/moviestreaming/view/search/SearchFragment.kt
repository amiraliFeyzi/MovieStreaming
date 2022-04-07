package com.example.moviestreaming.view.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.FragmentSearchBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import com.example.moviestreaming.view.home.adapter.MovieAdapter
import com.example.moviestreaming.view.moviedetail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment() , MovieAdapter.OnMovieClickListener {
    private var _binding:FragmentSearchBinding?=null
    private val binding get() = _binding!!

    private val viewModel:SearchViewModel by viewModels()

    @Inject
    lateinit var imageLoading: ImageLoading

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search()
        hideToolbar()
    }

    private fun search(){
        binding.etSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(nameMovie: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchMovie(nameMovie.toString())
                observeData()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun observeData(){
        viewModel.searchMovieListLiveData.observe(viewLifecycleOwner){
            setUpUi(it)
        }
    }

    private fun setUpUi(movies:List<Movie>){
        val searchAdapter = SearchAdapter(imageLoading , this)
        searchAdapter.setData(movies)
        binding.rvSearch.layoutManager = GridLayoutManager(requireContext() , 3)
        binding.rvSearch.adapter = searchAdapter
    }

    private fun hideToolbar(){
        //Hide BackBtn in toolbar
        binding.toolbar.showBackBtn =false
        binding.toolbar.checkBackBtnShow()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(requireContext() , MovieDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , movie)
        })
    }

    override fun onFavoriteBtnClick(movie: Movie) {

    }


}