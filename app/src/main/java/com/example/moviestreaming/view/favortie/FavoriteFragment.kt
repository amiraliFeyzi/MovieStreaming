package com.example.moviestreaming.view.favortie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.FragmentFavoriteBinding
import com.example.moviestreaming.model.dataclass.MovieEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class FavoriteFragment : BaseFragment(),FavoriteAdapter.OnImageMovieFavoriteClick {
    private var _binding: FragmentFavoriteBinding?=null
    private val binding get() = _binding!!

    private val viewModel:FavoriteViewModel by viewModels()

    @Inject
    lateinit var imageLoading: ImageLoading
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData(){
        viewModel.movieLiveData.observe(viewLifecycleOwner){
            setUpUi(it)
        }
    }

    private fun setUpUi(favoriteList:List<MovieEntity>){
        val favoriteAdapter =FavoriteAdapter(imageLoading , this)
        favoriteAdapter.setData(favoriteList)
        binding.viewPagerFavorite.adapter = favoriteAdapter

        //Hide Back Btn in Toolbar
        binding.toolbar.showBackBtn = false
        binding.toolbar.checkBackBtnShow()

        showDialogs(R.layout.dialog_favorite)
        setAnimationViewPager()
    }

    private fun setAnimationViewPager(){
        binding.viewPagerFavorite.setPageTransformer { page, position ->
            page.translationX = (-position*page.width)
            when {
                position <-1 -> {
                    page.alpha = 0F
                }
                position <= 0 -> {
                    page.alpha = 1F
                    page.pivotX = 0F
                    page.rotationY = (90 * abs(position))
                }
                position <=1 -> {
                    page.alpha = 1F
                    page.pivotX = page.width.toFloat()
                    page.rotationY = (-90 * abs(position))
                }
                else -> {
                    page.alpha = 0F
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLongClick(movieEntity: MovieEntity) {
        viewModel.removeItemFromFavorite(movieEntity)
    }


}