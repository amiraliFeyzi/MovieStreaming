package com.example.moviestreaming.view.user.buyaccount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestreaming.databinding.ItemBuyAccountBinding
import com.example.moviestreaming.model.dataclass.BuyAccount
import java.util.ArrayList

class BuyAccountAdapter(private val buyAccountClickListener: BuyAccountClickListener) :RecyclerView.Adapter<BuyAccountAdapter.BuyAccountViewHolder>() {

    private val buyAccountList = ArrayList<BuyAccount>()
    fun setData(buyAccountList:List<BuyAccount>){
        this.buyAccountList.clear()
        this.buyAccountList.addAll(buyAccountList)
        notifyDataSetChanged()
    }

    inner class BuyAccountViewHolder(val binding:ItemBuyAccountBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(buyAccount: BuyAccount){
            binding.tvCountBuyAcc.text = buyAccount.month
            binding.tvPriceBuyAcc.text = buyAccount.price

            binding.root.setOnClickListener {
                buyAccountClickListener.onClickBuyAccount(buyAccount , adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAccountViewHolder {
        return BuyAccountViewHolder(ItemBuyAccountBinding.inflate(LayoutInflater.from(parent.context) , parent ,false))

    }

    override fun onBindViewHolder(holder: BuyAccountViewHolder, position: Int) {
        holder.bind(buyAccountList[position])
    }

    override fun getItemCount(): Int  = buyAccountList.size

    interface BuyAccountClickListener{
        fun onClickBuyAccount(buyAccount: BuyAccount , position: Int)
    }
}