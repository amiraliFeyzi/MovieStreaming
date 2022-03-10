package com.example.moviestreaming.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.R
import com.example.moviestreaming.cumponents.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemMovieBinding
import com.example.moviestreaming.databinding.ItemPopularMovieBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.utils.variables.CATEGORY_NAME_SERIES
import com.example.moviestreaming.utils.variables.MOVIE_VIEW_TYPE
import com.example.moviestreaming.utils.variables.POPULAR_MOVIE_VIEW_TYPE

class MovieAdapter (private val imageLoading: ImageLoading):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieList = ArrayList<Movie>()

    private var viewType:Int = MOVIE_VIEW_TYPE

    fun setViewType(viewType: Int){
        this.viewType = viewType
        notifyDataSetChanged()
    }

    fun setData(movieList:List<Movie>){
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }
    inner class MovieViewHolder(val binding:ItemMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            if (!movie.rank.isNullOrEmpty()){
                binding.rankMovieTv.visibility = View.VISIBLE
                binding.rankMovieTv.text  = "rank: ${movie.rank}"
            }

            if (movie.category_name == CATEGORY_NAME_SERIES){
                binding.ivTime.setImageResource(R.drawable.ic_baseline_folder_special_24)
            }
            binding.nameMovieTv.text=movie.name
            binding.timeMoveTv.text = movie.time
            imageLoading.load(binding.ivMovie , movie.link_img)
        }
    }

    inner class PopularMovieViewHolder(val binding:ItemPopularMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.nameMovieTv.text=movie.name
            binding.timeMoveTv.text = movie.time
            imageLoading.load(binding.ivMovie , movie.link_img)

        }
    }


    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return when(viewType){
            MOVIE_VIEW_TYPE -> MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context) , parent ,false))
            POPULAR_MOVIE_VIEW_TYPE -> PopularMovieViewHolder(ItemPopularMovieBinding.inflate(
                LayoutInflater.from(parent.context) , parent ,false))
            else  -> throw IllegalStateException("viewType is not valid")

        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder){
            holder.bind(movieList[position])
        }else if (holder is PopularMovieViewHolder){
            holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int  = movieList.size

}