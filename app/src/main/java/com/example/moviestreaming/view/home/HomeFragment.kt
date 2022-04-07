package com.example.moviestreaming.view.home
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.FragmentHomeBinding
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider
import com.example.moviestreaming.utils.setHorizontalRecyclerView
import com.example.moviestreaming.utils.variables.*
import com.example.moviestreaming.view.home.adapter.MovieAdapter
import com.example.moviestreaming.view.home.adapter.SliderAdapter
import com.example.moviestreaming.view.moviedetail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(),MovieAdapter.OnMovieClickListener {
    private var _binding : FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private val viewModel:HomeViewModel by viewModels()


    @Inject
    lateinit var imageLoading: ImageLoading

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        moreTvOnclick()
    }


    private fun observeData(){
        getData()

        viewModel.sliderLiveData.observe(viewLifecycleOwner){
            setUpSlider(it)
        }

        viewModel.topMovieImdbLiveData.observe(viewLifecycleOwner){
            setTopMovieImdbList(it)
        }

        viewModel.newMovieLiveData.observe(viewLifecycleOwner){
            setNewMovieList(it)
        }

        viewModel.seriesLiveData.observe(viewLifecycleOwner){
            setSeriesList(it)
        }

        viewModel.popularMovieLiveData.observe(viewLifecycleOwner){
            setPopularMoveList(it)
        }

        viewModel.animationLiveData.observe(viewLifecycleOwner){
            setAnimationList(it)
        }

    }

    //call method get data from viewModel
    private fun getData(){
        viewModel.getSlider()
        viewModel.getTopMovieImdbList()
        viewModel.getNewMovieList()
        viewModel.getSeriesList()
        viewModel.getPopularMovieList()
        viewModel.getAnimationList()
    }


    private fun setUpSlider(sliders:List<Slider>){
        val sliderAdapter  = SliderAdapter(imageLoading)
        sliderAdapter.setData(sliders)
        binding.slider.adapter = sliderAdapter
        binding.sliderIndicator.setViewPager2(binding.slider)
    }

    private fun setTopMovieImdbList(topMovieImdbList:List<Movie>){
        val movieAdapter = MovieAdapter(imageLoading , this)
        binding.rvTopMovieImdb.setHorizontalRecyclerView(requireContext() , binding.rvTopMovieImdb)
        movieAdapter.setData(topMovieImdbList)
        binding.rvTopMovieImdb.adapter = movieAdapter
    }

    private fun setNewMovieList(newMovieList:List<Movie>){
        val movieAdapter = MovieAdapter(imageLoading , this)
        binding.rvNewMovie.setHorizontalRecyclerView(requireContext() , binding.rvNewMovie)
        movieAdapter.setData(newMovieList)
        binding.rvNewMovie.adapter = movieAdapter
    }

    private fun setSeriesList(seriesList:List<Movie>){
        val movieAdapter = MovieAdapter(imageLoading , this)
        binding.rvSeries.setHorizontalRecyclerView(requireContext() , binding.rvSeries)
        movieAdapter.setData(seriesList)
        binding.rvSeries.adapter = movieAdapter
    }

    private fun setPopularMoveList(popularMovieList:List<Movie>){
        val movieAdapter = MovieAdapter(imageLoading , this)
        movieAdapter.setViewType(POPULAR_MOVIE_VIEW_TYPE)
        binding.rvPopularMovie.setHorizontalRecyclerView(requireContext() , binding.rvPopularMovie)
        movieAdapter.setData(popularMovieList)
        binding.rvPopularMovie.adapter = movieAdapter
    }

    private fun setAnimationList(animationList:List<Movie>){
        val movieAdapter = MovieAdapter(imageLoading , this)
        binding.rvAnimation.setHorizontalRecyclerView(requireContext() , binding.rvAnimation)
        movieAdapter.setData(animationList)
        binding.rvAnimation.adapter = movieAdapter
    }

    private fun moreTvOnclick(){
        binding.moreTopMovieImdb.setOnClickListener(TvMoreClickListener(CATEGORY_NAME_TOP_MOVIE_IMDB , requireContext()))
        binding.moreNewMovie.setOnClickListener(TvMoreClickListener(CATEGORY_NAME_MOVIE_NEW , requireContext()))
        binding.moreSeries.setOnClickListener(TvMoreClickListener(CATEGORY_NAME_SERIES , requireContext()))
        binding.morePopularMovie.setOnClickListener(TvMoreClickListener(CATEGORY_NAME_POPULAR_MOVIE , requireContext()))
        binding.moreAnimation.setOnClickListener(TvMoreClickListener(CATEGORY_NAME_ANIMATION, requireContext()))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(requireContext() , MovieDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA , movie)
        })
    }

    override fun onFavoriteBtnClick(movie: Movie) {

    }


}