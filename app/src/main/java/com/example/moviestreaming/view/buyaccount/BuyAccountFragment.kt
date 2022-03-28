package com.example.moviestreaming.view.buyaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseFragment
import com.example.moviestreaming.databinding.FragmentBuyAccountBinding
import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.utils.setHorizontalRecyclerView
import com.example.moviestreaming.utils.variables.CALLBACK_URL
import com.example.moviestreaming.utils.variables.MERCHANT_ID
import com.zarinpal.ZarinPalBillingClient
import com.zarinpal.billing.purchase.Purchase
import com.zarinpal.client.BillingClientStateListener
import com.zarinpal.client.ClientState
import com.zarinpal.provider.core.future.FutureCompletionListener
import com.zarinpal.provider.core.future.TaskResult
import com.zarinpal.provider.model.response.Receipt
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BuyAccountFragment : BaseFragment(),BuyAccountAdapter.BuyAccountClickListener {
    private var _binding: FragmentBuyAccountBinding?=null
    private val binding get() = _binding!!

    private val viewModel:BuyAccountViewModel by viewModels()
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
        viewModel.buyAccountListLiveData.observe(viewLifecycleOwner){
            setUpUi(it)
        }
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

    private fun payment(price:Long){
        val client = ZarinPalBillingClient.newBuilder(requireActivity())
            .enableShowInvoice()
            .setListener(object: BillingClientStateListener {
                override fun onClientServiceDisconnected() {
                    Timber.i("onClientServiceDisconnected Finished")
                }

                override fun onClientSetupFinished(state: ClientState) {
                    Timber.i("onClientSetupFinished Finished")

                }

            } )
            .setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            .build()

        val purchase = Purchase.newBuilder()
            .asPaymentRequest(
                MERCHANT_ID,
                price,
                CALLBACK_URL,
                "${price}IRR Purchase"
            ).build()

        client.launchBillingFlow(purchase, object : FutureCompletionListener<Receipt> {
            override fun onComplete(task: TaskResult<Receipt>) {
                if (task.isSuccess) {
                    findNavController().navigate(R.id.action_buyAccountFragment_to_profileFragment)

                } else {
                    Toast.makeText(requireContext() , task.failure?.printStackTrace().toString() , Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onClickBuyAccount(buyAccount: BuyAccount) {
        payment(buyAccount.price.toLong())
    }


}