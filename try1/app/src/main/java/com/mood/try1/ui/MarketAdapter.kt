package com.mood.try1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mood.try1.databinding.MarketItemBinding
import com.mood.try1.domain.Market

class MarketAdapter : ListAdapter<Market, MarketAdapter.MarketHolder>(DiffCallback) {

    class MarketHolder(val viewDataBinding: MarketItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(market: Market) {
            viewDataBinding.market = market
            viewDataBinding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Market>() {
        override fun areItemsTheSame(oldItem: Market, newItem: Market): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Market, newItem: Market): Boolean {
            return oldItem == newItem
        }

    }

//    var markets: List<Market> = emptyList()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketHolder {


        return MarketHolder(MarketItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MarketHolder, position: Int) {
        val market = getItem(position)
        holder.bind(market)
    }



}