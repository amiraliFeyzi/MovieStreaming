package com.example.moviestreaming.view.player

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestreaming.databinding.ActivityPlayMovieBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayMovieActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPlayMovieBinding

    private val viewModel:PlayMovieViewModel by viewModels()

    private lateinit var exoPlayer: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()

    }

    private fun observeData(){

        viewModel.linkMovieLiveData.observe(this){
            setUpExoPlayer(it)
        }

    }

    //set player
    private fun setUpExoPlayer(uri:String){
        exoPlayer = ExoPlayer.Builder(this).build()
        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(uri))
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        exoPlayer.play()
        binding.playerView.player = exoPlayer
    }


    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}