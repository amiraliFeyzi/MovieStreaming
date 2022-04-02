package com.example.moviestreaming.view.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.`object`.UserInformation
import com.example.moviestreaming.model.dataclass.Comment
import com.example.moviestreaming.model.repository.comment.CommentRepository
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor( private val savedStateHandle: SavedStateHandle , private val commentRepository: CommentRepository):BaseViewModel() {
    private val _commentLiveData = MutableLiveData<List<Comment>>()

    val commentLiveData:LiveData<List<Comment>> get() = _commentLiveData

    private fun getIdMovie():Int{
        return savedStateHandle.get<Int>(EXTRA_KEY_DATA)!!
    }

    init {
        getCommentList()
    }

    private fun getCommentList(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            commentRepository.getCommentList(getIdMovie().toString()).collect{
                _commentLiveData.postValue(it)
            }
        }
    }

    fun addComment(comment:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            commentRepository.addComment(getIdMovie().toString(), comment, UserInformation.email!!)
        }
    }
}