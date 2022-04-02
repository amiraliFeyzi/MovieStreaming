package com.example.moviestreaming.view.user.buyaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.databinding.FragmentBuyAccountBinding
import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.utils.setHorizontalRecyclerView
import com.example.moviestreaming.view.user.AccountUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyAccountFragment : BaseFragment(),BuyAccountAdapter.BuyAccountClickListener {
    private var _binding: FragmentBuyAccountBinding?=null
    private val binding get() = _binding!!

    private val viewModel: AccountUserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBuyAccountBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeData()

    }

    private fun observeData(){
        getData()
        viewModel.buyAccountListLiveData.observe(viewLifecycleOwner){
            setUpUi(it)
        }

    }

    //call getData method From ViewModel
    private fun getData(){
        viewModel.getListBuyAccount()
        viewModel.getSubscriptionUserFromServer()
    }

    private fun setUpUi(buyAccountList:List<BuyAccount>){
        val buyAccountAdapter = BuyAccountAdapter(this)
        buyAccountAdapter.setData(buyAccountList)
        binding.rvBuyAcc.setHorizontalRecyclerView(requireContext() , binding.rvBuyAcc)
        binding.rvBuyAcc.adapter = buyAccountAdapter

        binding.toolbar.showBackBtn = false
        binding.toolbar.checkBackBtnShow()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    override fun onClickBuyAccount(buyAccount: BuyAccount , position:Int) {
        when(position){
            0-> {
                viewModel.buyAccountByUser(2592000000 , "+30 Days")
            }
            1->{
                viewModel.buyAccountByUser(7776000000 , "+90 Days")
            }
            2->{
                viewModel.buyAccountByUser(15552000000 , "+270 Days")
            }
        }
    }


}