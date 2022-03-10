package com.example.moviestreaming.customview.imageview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class MovieImageView  :androidx.appcompat.widget.AppCompatImageView {


    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!,
        attrs,
        defStyleAttr)


}