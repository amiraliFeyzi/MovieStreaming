package com.example.moviestreaming.model.repository.comment

import com.example.moviestreaming.model.dataclass.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    suspend fun addComment(idMovie: String ,comment:String ,email:String)

    suspend fun getCommentList(id_item:String):Flow<List<Comment>>
}