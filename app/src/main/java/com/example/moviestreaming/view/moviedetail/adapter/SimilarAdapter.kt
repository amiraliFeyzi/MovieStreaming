package com.example.moviestreaming.view.moviedetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.R
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemMovieBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.utils.variables.CATEGORY_NAME_SERIES
import com.example.moviestreaming.view.home.adapter.MovieAdapter

class SimilarAdapter (private val imageLoading: ImageLoading, private val onMovieClickListener: MovieAdapter.OnMovieClickListener,val size:Int):RecyclerView.Adapter<SimilarAdapter.MovieViewHolder>() {

    private val movieList = ArrayList<Movie>()

    fun setData(movieList: List<Movie>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            if (!movie.rank.isNullOrEmpty()) {
                binding.rankMovieTv.visibility = View.VISIBLE
                binding.rankMovieTv.text = "rank: ${movie.rank}"
            }

            if (movie.category_name == CATEGORY_NAME_SERIES) {
                binding.ivTime.setImageResource(R.drawable.ic_baseline_folder_special_24)
            }
            binding.nameMovieTv.text = movie.name
            binding.timeMoveTv.text = movie.time
            imageLoading.load(binding.ivMovie, movie.link_img)

            binding.root.setOnClickListener {
                onMovieClickListener.onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }



    override fun getItemCount(): Int  = size


}