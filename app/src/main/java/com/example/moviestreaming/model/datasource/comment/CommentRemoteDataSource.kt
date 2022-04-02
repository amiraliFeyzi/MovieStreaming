package com.example.moviestreaming.model.datasource.comment

import com.example.moviestreaming.model.dataclass.Comment
import com.example.moviestreaming.model.network.ApiService

class CommentRemoteDataSource(private val apiService: ApiService):CommentDataSource {

    override suspend fun addComment(idMovie: String, comment: String, email: String) =
        apiService.addComment(idMovie , comment, email)

    override suspend fun getCommentList(id_item: String): List<Comment> =
        apiService.getCommentList(id_item)
}