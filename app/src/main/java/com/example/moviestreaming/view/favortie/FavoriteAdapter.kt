package com.example.moviestreaming.view.favortie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemFavoriteBinding
import com.example.moviestreaming.model.dataclass.MovieEntity
import java.util.ArrayList

class FavoriteAdapter (private val imageLoading: ImageLoading  , val onImageMovieFavoriteClick:OnImageMovieFavoriteClick):RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val favoriteList =ArrayList<MovieEntity>()
    fun setData(favorites:List<MovieEntity>){
        this.favoriteList.clear()
        this.favoriteList.addAll(favorites)
        notifyDataSetChanged()
    }
    inner class FavoriteViewHolder(val binding:ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movieEntity: MovieEntity){
            binding.tvNameMovie.text = movieEntity.name
            imageLoading.loadWithCorners(binding.ivMovieMain , movieEntity.link_img , 20f)
            imageLoading.loadWithCorners(binding.ivMovieCard , movieEntity.link_img , 20f)

            binding.ivMovieCard.setOnLongClickListener {
                onImageMovieFavoriteClick.onLongClick(movieEntity)
                removeItemFromFavorite(movieEntity , adapterPosition)
                return@setOnLongClickListener false
            }


            binding.ivMovieMain.setOnLongClickListener {
                onImageMovieFavoriteClick.onLongClick(movieEntity)
                removeItemFromFavorite(movieEntity , adapterPosition)
                return@setOnLongClickListener false
            }
        }
    }

    private fun removeItemFromFavorite(movieEntity: MovieEntity , position: Int){
        favoriteList.remove(movieEntity)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int  = favoriteList.size


    interface OnImageMovieFavoriteClick{
        fun onLongClick(movieEntity: MovieEntity)
    }
}