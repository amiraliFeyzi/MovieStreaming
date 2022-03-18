package com.example.moviestreaming.customview.toolbar

import android.content.Context
import android.media.Image
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import com.example.moviestreaming.R

class MovieToolbar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    //finish Activity when click backBtn Handle
    var onBackClickListener :View.OnClickListener? = null
    set(value) {
        field = value
        findViewById<ImageView>(R.id.backBtn).setOnClickListener(onBackClickListener)
    }

    var showBackBtn = true
    fun checkBackBtnShow(){
        if (showBackBtn){
            findViewById<ImageView>(R.id.backBtn).visibility = View.VISIBLE
        }else{
            findViewById<ImageView>(R.id.backBtn).visibility = View.INVISIBLE
        }
    }

    init {
        setToolbar(context , attrs)
    }



    //This function set Text Toolbar
    private fun setToolbar(context: Context , attrs: AttributeSet?){
        inflate(context , R.layout.view_toolbar , this)
        attrs?.let {
            val getToolbar =  context.obtainStyledAttributes(attrs , R.styleable.MovieToolbar)
            val title = getToolbar.getString(R.styleable.MovieToolbar_tittleToolbar)
            if (title != null && title.isNotEmpty()){
                findViewById<TextView>(R.id.tvTittleToolbar).text = title
            }

           getToolbar.recycle()
        }

    }
}