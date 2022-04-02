package com.example.moviestreaming.view.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.databinding.ItemCommentBinding
import com.example.moviestreaming.model.dataclass.Comment
import java.util.ArrayList

class CommentAdapter :RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    private val commentList = ArrayList<Comment>()
    fun setData(comments:List<Comment>){
        this.commentList.clear()
        this.commentList.addAll(comments)
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(val binding:ItemCommentBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(comment: Comment){
            binding.txtComment.text = comment.comment
            binding.txtEmail.text = "${comment.email}:"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int  =commentList.size
}