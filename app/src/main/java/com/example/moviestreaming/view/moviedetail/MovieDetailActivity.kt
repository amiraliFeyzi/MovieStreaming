package com.example.moviestreaming.view.moviedetail

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.URLUtil
import androidx.activity.viewModels
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseActivity
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ActivityMovieDetailBinding
import com.example.moviestreaming.model.dataclass.Cast
import com.example.moviestreaming.model.dataclass.DetailMovie
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Season
import com.example.moviestreaming.utils.setHorizontalRecyclerView
import com.example.moviestreaming.utils.variables.CATEGORY_NAME_SERIES
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import com.example.moviestreaming.view.comment.CommentActivity
import com.example.moviestreaming.view.episode.EpisodeActivity
import com.example.moviestreaming.view.home.adapter.MovieAdapter
import com.example.moviestreaming.view.moviedetail.adapter.CastMovieAdapter
import com.example.moviestreaming.view.moviedetail.adapter.SeasonMovieAdapter
import com.example.moviestreaming.view.moviedetail.adapter.SimilarAdapter
import com.example.moviestreaming.view.player.PlayMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() , MovieAdapter.OnMovieClickListener , SeasonMovieAdapter.OnSeasonClickListener {
    private lateinit var binding:ActivityMovieDetailBinding

    private val viewModel:MovieDetailViewModel by viewModels()

    @Inject
    lateinit var imageLoading: ImageLoading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
    }

    private fun observeData(){
        getData()
        viewModel.movieLiveData.observe(this){
            setUpUiMovie(it)

        }

        viewModel.detailMovieLiveData.observe(this){
            it.forEach { detailMovie->
                setUpUiDetailMovie(detailMovie)
                downloadMovie(detailMovie.link_movie)
            }
        }

        viewModel.seasonMovieLiveData.observe(this){
            setSeasonMovie(it)
        }

        viewModel.castMovieLiveData.observe(this){
            setCastMovie(it)
        }

        viewModel.similarMovieLiveData.observe(this){
            setSimilarMovie(it)
        }

    }

    private fun downloadMovie(uri:String){
        binding.ivDownload.setOnClickListener {
            val request = DownloadManager.Request(Uri.parse(uri))
            val title = URLUtil.guessFileName(uri , null , null )
            request.setTitle(title)
            request.setDescription(getString(R.string.downloadFileWaiting))
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS , title)
            val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
            showToast(getString(R.string.downloadStarted))
        }

    }

    //call method getData from viewModel
    private fun getData(){
        viewModel.getDetailMovie()
        viewModel.getSeasonMovie()
        viewModel.getCastMovie()
        viewModel.getSimilarMovie()
    }

    private fun setUpUiMovie(movie:Movie){
        binding.tvNameMovieDetail.text = movie.name
        binding.tvDirectorDetailMovie.text = "Director: ${movie.director}"
        binding.tvPublishedDetailMovie.text = "Published: ${movie.published}"
        binding.tvTimeMovieDetail.text = movie.time

        if (movie.category_name == CATEGORY_NAME_SERIES){
            binding.ivTimeDetail.setImageResource(R.drawable.ic_baseline_folder_special_24)
            binding.tvTimeMovieDetail.text = movie.time
        }

        binding.tvRateImdbMovieDetail.text = "Imdb: ${movie.rate_imdb}"

        binding.ivComment.setOnClickListener {
            startActivity(Intent(this , CommentActivity::class.java).apply {
                putExtra(EXTRA_KEY_DATA , movie.id)
            })
        }


    }

    private fun setUpUiDetailMovie(detailMovie: DetailMovie){
        imageLoading.load(binding.ivMovieDetail , detailMovie.link_img)
        binding.tvDescriptionDetailMovie.text = detailMovie.description
        binding.ivPlayMovie.setOnClickListener {
            startActivity(Intent(this , PlayMovieActivity::class.java).apply {
                putExtra(EXTRA_KEY_DATA ,detailMovie.link_movie)
            })
        }



        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setSeasonMovie(seasons:List<Season>){
        val seasonMovieAdapter = SeasonMovieAdapter(imageLoading , this)
        seasonMovieAdapter.setData(seasons)
        binding.rvSeasonMovieDetail.setHorizontalRecyclerView(this , binding.rvSeasonMovieDetail)
        binding.rvSeasonMovieDetail.adapter = seasonMovieAdapter
    }

    private fun setCastMovie(casts:List<Cast>){
        val castMovieAdapter = CastMovieAdapter(imageLoading)
        castMovieAdapter.setData(casts)
        binding.rvCastDetail.setHorizontalRecyclerView(this , binding.rvCastDetail)
        binding.rvCastDetail.adapter = castMovieAdapter
    }

    private fun setSimilarMovie(movies:List<Movie>){
        val similarAdapter = SimilarAdapter(imageLoading , this , getRandom())
        similarAdapter.setData(movies)
        binding.rvSimilarMovie.setHorizontalRecyclerView(this , binding.rvSimilarMovie)
        binding.rvSimilarMovie.adapter = similarAdapter
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(this , MovieDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , movie)
        })
    }

    override fun onFavoriteBtnClick(movie: Movie) {

    }

    private fun getRandom():Int{
        return (1..7).random()
    }

    override fun onSeasonClick(season: Season) {
        startActivity(Intent(this , EpisodeActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , season.id)
        })
    }


}