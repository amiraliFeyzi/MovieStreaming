package com.example.moviestreaming.view.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ItemIntroBinding
import com.example.moviestreaming.model.dataclass.Intro
import java.util.ArrayList

class IntroAdapter (private val imageLoading: ImageLoading):RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {

    private val introList = ArrayList<Intro>()
    fun setData(intros:List<Intro>){
        this.introList.clear()
        this.introList.addAll(intros)
        notifyDataSetChanged()
    }
    inner class IntroViewHolder(val binding:ItemIntroBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(intro:Intro){
            binding.tvIntro.text = intro.description
            imageLoading.loadCircle(binding.ivIntro , intro.link_img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
       return IntroViewHolder(ItemIntroBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
      holder.bind(introList[position])
    }

    override fun getItemCount(): Int  = introList.size
}