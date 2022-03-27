package com.example.moviestreaming.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setHorizontalRecyclerView(context: Context, recyclerView: RecyclerView){
    recyclerView.layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL ,false)
}

fun RecyclerView.setLinearRecyclerView(context: Context, recyclerView: RecyclerView){
    recyclerView.layoutManager = LinearLayoutManager(context , RecyclerView.VERTICAL ,false)
}


