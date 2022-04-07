package com.example.moviestreaming.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemSearchBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.view.home.adapter.MovieAdapter

class SearchAdapter (private val imageLoading: ImageLoading ,private val onMovieClickListener: MovieAdapter.OnMovieClickListener):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val movieList = ArrayList<Movie>()
    fun setData(movies:List<Movie>){
        this.movieList.clear()
        this.movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(val binding:ItemSearchBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie:Movie){
            binding.tvNameSearch.text = movie.name
            imageLoading.loadWithCorners(binding.ivSearch , movie.link_img , 8f)

            binding.root.setOnClickListener {
                onMovieClickListener.onClick(movie)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
       return SearchViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context) , parent ,false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int  = movieList.size

}