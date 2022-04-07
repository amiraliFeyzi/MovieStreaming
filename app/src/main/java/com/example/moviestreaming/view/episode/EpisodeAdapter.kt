package com.example.moviestreaming.view.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemEpisodeBinding
import com.example.moviestreaming.model.dataclass.Episode
import java.util.ArrayList

class EpisodeAdapter (private val imageLoading: ImageLoading , private val onEpisodeClickListener: OnEpisodeClickListener):RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private val episodeList = ArrayList<Episode>()

    fun setData(episodes:List<Episode>){
        this.episodeList.clear()
        this.episodeList.addAll(episodes)
        notifyDataSetChanged()
    }
    inner class EpisodeViewHolder(val binding:ItemEpisodeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(episode:Episode){
            binding.tvNameEpisode.text = episode.name
            binding.tvDetailEpisode.text = episode.detail
            binding.tvTimeEpisode.text = episode.time
            imageLoading.load(binding.ivEpisode , episode.link_img)

            binding.root.setOnClickListener {
                onEpisodeClickListener.onClickEpisode(episode)
            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
       holder.bind(episodeList[position])
    }

    override fun getItemCount(): Int  = episodeList.size

    interface OnEpisodeClickListener{
        fun onClickEpisode(episode: Episode)
    }

}