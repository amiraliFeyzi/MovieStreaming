package com.example.moviestreaming.model.datasource.comment

import com.example.moviestreaming.model.dataclass.Comment


interface CommentDataSource {

    suspend fun addComment(idMovie: String ,comment:String ,email:String)

    suspend fun getCommentList(id_item:String):List<Comment>
}