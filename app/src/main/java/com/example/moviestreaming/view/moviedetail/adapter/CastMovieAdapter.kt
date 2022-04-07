package com.example.moviestreaming.view.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemCastBinding
import com.example.moviestreaming.model.dataclass.Cast
import java.util.ArrayList

class CastMovieAdapter (private val imageLoading: ImageLoading):RecyclerView.Adapter<CastMovieAdapter.CastViewHolder>() {

    private val castList = ArrayList<Cast>()
    fun setData(casts:List<Cast>){
        this.castList.clear()
        this.castList.addAll(casts)
        notifyDataSetChanged()
    }


    inner class CastViewHolder(val binding:ItemCastBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(cast:Cast){
            binding.tvNameCast.text = cast.name
            imageLoading.loadCircle(binding.ivCast , cast.link_img)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(ItemCastBinding.inflate(LayoutInflater.from(parent.context) , parent ,false))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    override fun getItemCount(): Int  = castList.size

}