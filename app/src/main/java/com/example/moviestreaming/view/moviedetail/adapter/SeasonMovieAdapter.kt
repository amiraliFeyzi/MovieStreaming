package com.example.moviestreaming.view.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemSeasonBinding
import com.example.moviestreaming.model.dataclass.Season

class SeasonMovieAdapter(private val imageLoading: ImageLoading , private val onSeasonClickListener: OnSeasonClickListener) :RecyclerView.Adapter<SeasonMovieAdapter.SeasonViewHolder>() {
    private val seasonList= ArrayList<Season>()
    fun setData(seasons:List<Season>){
        this.seasonList.clear()
        this.seasonList.addAll(seasons)
        notifyDataSetChanged()
    }

    inner class SeasonViewHolder(val binding:ItemSeasonBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(season: Season){
            binding.tvNumberSeason.text = "Season: ${season.number_season}"
            binding.tvCountEpisodeSeasonMovie.text = "Episodes: ${season.count_episodes}"

            imageLoading.load(binding.ivMovieSeason , season.link_img_season)

            binding.root.setOnClickListener {
                onSeasonClickListener.onSeasonClick(season)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(ItemSeasonBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(seasonList[position])
    }

    override fun getItemCount(): Int  = seasonList.size

    interface OnSeasonClickListener{
        fun onSeasonClick(season: Season)
    }
}