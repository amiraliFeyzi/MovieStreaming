package com.example.moviestreaming.view.movielist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestreaming.R
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ActivityMovieListBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.MovieEntity
import com.example.moviestreaming.utils.setLinearRecyclerView
import com.example.moviestreaming.utils.variables.*
import com.example.moviestreaming.view.home.adapter.MovieAdapter
import com.example.moviestreaming.view.moviedetail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_toolbar.view.*
import javax.inject.Inject

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity(),MovieAdapter.OnMovieClickListener {

    private lateinit var binding:ActivityMovieListBinding

    @Inject
    lateinit var imageLoading:ImageLoading
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
        val movieAdapter = MovieAdapter(imageLoading , this )
        movieAdapter.setData(movieList)
        movieAdapter.setViewType(MOVIE_LIST_VIEW_TYPE)

        binding.rvMovie.adapter = movieAdapter
        when(movieList[0].category_name){
            CATEGORY_NAME_TOP_MOVIE_IMDB -> binding.toolbar.tvTittleToolbar.text = getString(R.string.top_movie_imdb)
            CATEGORY_NAME_MOVIE_NEW -> binding.toolbar.tvTittleToolbar.text = getString(R.string.new_movie)
            CATEGORY_NAME_SERIES -> binding.toolbar.tvTittleToolbar.text = getString(R.string.series)
            CATEGORY_NAME_POPULAR_MOVIE -> binding.toolbar.tvTittleToolbar.text = getString(R.string.popular_movie)
            CATEGORY_NAME_ANIMATION -> binding.toolbar.tvTittleToolbar.text = getString(R.string.animation)
        }

        binding.toolbar.onBackClickListener = View.OnClickListener {
            finish()
        }
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(this , MovieDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , movie)
        })
    }

    override fun onFavoriteBtnClick(movie: Movie) {
        if(movie.isFavorite){
            val movieEntity  = MovieEntity(movie.category_name , movie.id, movie.link_img , movie.name)
            viewModel.deleteMovieFromFavorite(movieEntity)
        }else{
            val movieEntity  = MovieEntity(movie.category_name , movie.id , movie.link_img , movie.name)
            viewModel.addMovieToFavorite(movieEntity)
        }
    }
}