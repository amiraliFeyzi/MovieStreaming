package com.example.moviestreaming.view.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemGenreBinding
import com.example.moviestreaming.model.dataclass.Genre
import java.util.ArrayList

class GenreAdapter (private val imageLoading: ImageLoading , private val onGenreClickListener: OnGenreClickListener):RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private val genreList = ArrayList<Genre>()
    fun setData(genres:List<Genre>){
        this.genreList.clear()
        this.genreList.addAll(genres)
        notifyDataSetChanged()
    }
    inner class GenreViewHolder(val binding:ItemGenreBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(genre: Genre){
            binding.tvNameGenre.text = genre.name
            imageLoading.load(binding.ivGenre   , genre.link_img)

            binding.root.setOnClickListener {
                onGenreClickListener.onGenreClick(genre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context) , parent ,false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun getItemCount(): Int  = genreList.size

    interface OnGenreClickListener{
        fun onGenreClick(genre: Genre)
    }
}