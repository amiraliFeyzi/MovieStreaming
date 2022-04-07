package com.example.moviestreaming.view.episode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ActivityEpisodeBinding
import com.example.moviestreaming.model.dataclass.Episode
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import com.example.moviestreaming.view.player.PlayMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EpisodeActivity : AppCompatActivity(),EpisodeAdapter.OnEpisodeClickListener {
    private lateinit var binding:ActivityEpisodeBinding

    private val viewModel:EpisodeViewModel by viewModels()

    @Inject
    lateinit var imageLoading: ImageLoading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeData()
    }

    private fun observeData(){
        viewModel.episodeLivaData.observe(this){
            setEpisodes(it)
        }
    }

    private fun setEpisodes(episodes:List<Episode>){
        val episodeAdapter = EpisodeAdapter(imageLoading , this)
        episodeAdapter.setData(episodes)
        binding.rvEpisodes.layoutManager = GridLayoutManager(this , 2)
        binding.rvEpisodes.adapter = episodeAdapter

        binding.toolbar.onBackClickListener = View.OnClickListener {
            finish()
        }

    }

    override fun onClickEpisode(episode: Episode) {
        startActivity(Intent(this , PlayMovieActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , episode.link_episodes)
        })
    }

}