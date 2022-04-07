package com.example.moviestreaming.view.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseActivity
import com.example.moviestreaming.databinding.ActivityCommentBinding
import com.example.moviestreaming.model.dataclass.Comment
import com.example.moviestreaming.utils.setLinearRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentActivity : BaseActivity() {
    private lateinit var binding:ActivityCommentBinding

    private val viewModel:CommentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
        sendComment()
    }

    private fun observeData(){
        viewModel.commentLiveData.observe(this){
            setUpUi(it)
        }
    }

    private fun setUpUi(comments:List<Comment>){
        val commentAdapter =CommentAdapter()
        commentAdapter.setData(comments)
        binding.rvComment.setLinearRecyclerView(this , binding.rvComment)
        binding.rvComment.adapter = commentAdapter
    }

    private fun sendComment(){
        binding.ivSendComment.setOnClickListener {
            if (binding.etComment.text.isNotEmpty()){
                viewModel.addComment(binding.etComment.text.toString())
                showToast(getString(R.string.sendComment))
                binding.etComment.setText("")
            }else{
                showToast(getString(R.string.textIsNull))
            }



        }
    }
}