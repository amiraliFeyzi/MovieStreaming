package com.example.moviestreaming.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog

interface BaseView {
    val rootContext:Context


    fun showDialogs(layoutRedId:Int): View {
        rootContext?.let {myContext->
            var dialog: AlertDialog.Builder = AlertDialog.Builder(myContext)
            val view = LayoutInflater.from(myContext).inflate(layoutRedId , null)
            dialog.setView(view)
            val alertDialog:AlertDialog = dialog.create()
            alertDialog.show()

            return view

        }

    }
}