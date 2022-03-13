package com.example.moviestreaming.view.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviestreaming.R
import com.example.moviestreaming.databinding.ActivityMovieListBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.utils.setLinearRecyclerView
import com.example.moviestreaming.utils.variables.*
import com.example.moviestreaming.view.home.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_toolbar.view.*

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMovieListBinding

    private val viewModel:MovieListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
    }

    private fun observeData(){
        viewModel.getMovieList()

        viewModel.movieList.observe(this){
            setUpUi(it)

        }
    }


    private fun setUpUi(movieList:List<Movie>){
        binding.rvMovie.setLinearRecyclerView(this , binding.rvMovie)
        val movieAdapter = MovieListAdapter()
        movieAdapter.setData(movieList)
        binding.rvMovie.adapter = movieAdapter

        when(movieList[0].category_name){
            CATEGORY_NAME_TOP_MOVIE_IMDB -> binding.toolbar.tvTittleToolbar.text = getString(R.string.top_movie_imdb)
            CATEGORY_NAME_MOVIE_NEW -> binding.toolbar.tvTittleToolbar.text = getString(R.string.new_movie)
            CATEGORY_NAME_SERIES -> binding.toolbar.tvTittleToolbar.text = getString(R.string.series)
            CATEGORY_NAME_POPULAR_MOVIE -> binding.toolbar.tvTittleToolbar.text = getString(R.string.popular_movie)
            CATEGORY_NAME_ANIMATION -> binding.toolbar.tvTittleToolbar.text = getString(R.string.animation)
        }
    }
}