package com.example.moviestreaming.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemSliderBinding
import com.example.moviestreaming.model.dataclass.Slider
import java.util.ArrayList
import javax.inject.Inject

class SliderAdapter @Inject constructor(private val imageLoading: ImageLoading) :RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val sliders = ArrayList<Slider>()

    fun setData(sliders:List<Slider>){
        this.sliders.clear()
        this.sliders.addAll(sliders)
        notifyDataSetChanged()
    }
    inner class SliderViewHolder(val binding:ItemSliderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(slider:Slider){
            imageLoading.load(binding.ivSlider , slider.link_img)
            binding.tvNameSlider.text = slider.name
            binding.tvMoviePublishedSlider.text = slider.published
            binding.tvTimeMovieSlider.text = slider.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(ItemSliderBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliders[position])
    }

    override fun getItemCount(): Int  = sliders.size
}