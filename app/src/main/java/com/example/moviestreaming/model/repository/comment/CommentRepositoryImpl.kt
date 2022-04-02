package com.example.moviestreaming.model.repository.comment

import com.example.moviestreaming.model.dataclass.Comment
import com.example.moviestreaming.model.datasource.comment.CommentDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CommentRepositoryImpl(private val commentRemoteDataSource: CommentDataSource):CommentRepository {

    override suspend fun addComment(idMovie: String, comment: String, email: String) =
        commentRemoteDataSource.addComment(idMovie, comment, email)

    override suspend fun getCommentList(id_item: String): Flow<List<Comment>> {
        return flow {
            emit(commentRemoteDataSource.getCommentList(id_item))
        }
    }
}