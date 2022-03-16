package com.example.moviestreaming.view.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.R
import com.example.moviestreaming.cumponents.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemMovieListBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.utils.variables.CATEGORY_NAME_SERIES
import com.example.moviestreaming.utils.variables.CATEGORY_NAME_TOP_MOVIE_IMDB
import com.example.moviestreaming.view.home.adapter.MovieAdapter

class MovieListAdapter (private val imageLoading: ImageLoading):RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    private val movieList = ArrayList<Movie>()

    fun setData(movieList:List<Movie>){
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

     var onMovieClickListener:MovieAdapter.OnMovieClickListener? = null

    inner class MovieListViewHolder(val binding:ItemMovieListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie:Movie){
            if (movie.category_name == CATEGORY_NAME_TOP_MOVIE_IMDB){
                binding.tvRankMoveList.visibility = View.VISIBLE
                binding.tvRankMoveList.text = "rank: ${movie.rank}"
            }

            if(movie.category_name == CATEGORY_NAME_SERIES){
                binding.ivTime.setImageResource(R.drawable.ic_baseline_folder_special_24)
            }

            imageLoading.loadWithCorners(binding.ivMovieList , movie.link_img , 20f)
            binding.tvNameMovieList.text = movie.name
            binding.tvDirectorMovieList.text = "Director: ${movie.director}"
            binding.tvPublishedMovieList.text = "Published: ${movie.published}"
            binding.tvRateImdbMovieList.text = "Imdb: ${movie.rate_imdb}"
            binding.tvTimeMovieList.text = movie.time



            binding.root.setOnClickListener {
                onMovieClickListener?.onClick(movie)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(ItemMovieListBinding.inflate(LayoutInflater.from(parent.context) , parent ,false))
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int =movieList.size

}