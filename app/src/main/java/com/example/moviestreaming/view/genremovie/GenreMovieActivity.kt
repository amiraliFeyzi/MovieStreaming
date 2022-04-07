package com.example.moviestreaming.view.genremovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ActivityGenreMovieBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.utils.setLinearRecyclerView
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import com.example.moviestreaming.utils.variables.MOVIE_LIST_VIEW_TYPE
import com.example.moviestreaming.view.home.adapter.MovieAdapter
import com.example.moviestreaming.view.moviedetail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GenreMovieActivity : AppCompatActivity() , MovieAdapter.OnMovieClickListener {
    private lateinit var binding:ActivityGenreMovieBinding
    private val viewModel:GenreMovieViewModel by viewModels()
    @Inject
    lateinit var imageLoading: ImageLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
    }

    private fun observeData(){
        viewModel.genreMovieLiveData.observe(this){
            setUpUi(it)
        }

    }

    private fun setUpUi(movies:List<Movie>){
        val movieAdapter = MovieAdapter(imageLoading,this)
        movieAdapter.setData(movies)
        movieAdapter.setViewType(MOVIE_LIST_VIEW_TYPE)
        binding.rvGenreMovie.setLinearRecyclerView(this , binding.rvGenreMovie)
        binding.rvGenreMovie.adapter = movieAdapter
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(this , MovieDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , movie)
        })
    }

    override fun onFavoriteBtnClick(movie: Movie) {
    }


}