package com.example.moviestreaming.view.user.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.databinding.FragmentProfileBinding
import com.example.moviestreaming.model.`object`.UserInformation
import com.example.moviestreaming.utils.convertUnixTimeToDay
import com.example.moviestreaming.view.user.AccountUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding?=null
    private val binding get() = _binding!!

    private val viewModel: AccountUserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSubscriptionUserFromServer()
        observeData()

    }

    private fun observeData(){

        viewModel.paymentLiveData.observe(viewLifecycleOwner){

            var subscriptionTime = it.subscription  - System.currentTimeMillis()

            val daySubscription = convertUnixTimeToDay(subscriptionTime)
            if (daySubscription >0){
                binding.tvCountAccount.text = "$daySubscription Days"
            }else{
                binding.tvCountAccount.text = getString(R.string.nothing)
            }
            if (it.chargeTime.isNotBlank()){
                binding.tvChargeTime.visibility = View.VISIBLE
                binding.tvTittleChargeAccount.visibility = View.VISIBLE
                binding.tvChargeTime.text = it.chargeTime
            }


        }


        setUpUi()
    }

    private fun setUpUi(){
        binding.tvEmailProfile.text = UserInformation.email!!

        binding.toolbar.showBackBtn =false
        binding.toolbar.checkBackBtnShow()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}