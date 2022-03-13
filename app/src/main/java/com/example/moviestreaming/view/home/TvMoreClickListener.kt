package com.example.moviestreaming.view.home

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.moviestreaming.utils.variables.EXTRA_KEY_CATEGORY
import com.example.moviestreaming.view.movielist.MovieListActivity

class TvMoreClickListener(private val categoryName:String, private  val context: Context ):View.OnClickListener {

    override fun onClick(p0: View?) {
        context.startActivity(Intent(context , MovieListActivity::class.java)
            .apply {
                putExtra(EXTRA_KEY_CATEGORY , categoryName)
            })
    }


}