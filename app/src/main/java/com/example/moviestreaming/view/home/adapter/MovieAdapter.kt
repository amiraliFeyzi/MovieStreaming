package com.example.moviestreaming.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.R
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemMovieBinding
import com.example.moviestreaming.databinding.ItemMovieListBinding
import com.example.moviestreaming.databinding.ItemPopularMovieBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.utils.variables.*

class MovieAdapter (private val imageLoading: ImageLoading , private val onMovieClickListener: OnMovieClickListener):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

            binding.root.setOnClickListener {
                onMovieClickListener.onClick(movie)
            }
        }
    }

    inner class PopularMovieViewHolder(val binding:ItemPopularMovieBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.nameMovieTv.text=movie.name
            binding.timeMoveTv.text = movie.time
            imageLoading.load(binding.ivMovie , movie.link_img)

            binding.root.setOnClickListener {
                onMovieClickListener.onClick(movie)
            }

        }
    }

    inner class MovieListViewHolder(val binding: ItemMovieListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie:Movie){
            if (movie.category_name == CATEGORY_NAME_TOP_MOVIE_IMDB){
                binding.tvRankMoveList.visibility = View.VISIBLE
                binding.tvRankMoveList.text = "rank: ${movie.rank}"
            }

            if(movie.category_name == CATEGORY_NAME_SERIES){
                binding.ivTime.setImageResource(R.drawable.ic_baseline_folder_special_24)
            }

            if (movie.isFavorite){
                binding.ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }else{
                binding.ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            imageLoading.loadWithCorners(binding.ivMovieList , movie.link_img , 20f)
            binding.tvNameMovieList.text = movie.name
            binding.tvDirectorMovieList.text = "Director: ${movie.director}"
            binding.tvPublishedMovieList.text = "Published: ${movie.published}"
            binding.tvRateImdbMovieList.text = "Imdb: ${movie.rate_imdb}"
            binding.tvTimeMovieList.text = movie.time



            binding.root.setOnClickListener {
                onMovieClickListener.onClick(movie)
            }

            binding.ivFavorite.setOnClickListener {
                onMovieClickListener.onFavoriteBtnClick(movie)
                movie.isFavorite = !movie.isFavorite
                notifyItemChanged(adapterPosition)
            }


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

            MOVIE_LIST_VIEW_TYPE -> MovieListViewHolder(ItemMovieListBinding.inflate(LayoutInflater.from(parent.context),
            parent , false))
            else  -> throw IllegalStateException("viewType is not valid")

        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(movieList[position])
            }
            is PopularMovieViewHolder -> {
                holder.bind(movieList[position])
            }
            is MovieListViewHolder -> {
                holder.bind(movieList[position])
            }
        }
    }

    override fun getItemCount(): Int  = movieList.size

    interface OnMovieClickListener{
        fun onClick(movie: Movie)
        fun onFavoriteBtnClick(movie: Movie)
    }

}