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

fun convertUnixTimeToDay(unixTime:Long):Long{
    var unix = unixTime
    unix /= 24
    unix /= 60
    unix /= 60
    unix /= 1000

    return unix
}


