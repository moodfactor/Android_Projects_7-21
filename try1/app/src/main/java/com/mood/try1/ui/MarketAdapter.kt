package com.mood.try1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mood.try1.R
import com.mood.try1.databinding.MarketItemBinding
import com.mood.try1.domain.Market

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.MarketHolder>() {

    class MarketHolder(val viewDataBinding: MarketItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.market_item
        }
    }

    var markets: List<Market> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketHolder {
        val withDataBinding: MarketItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MarketHolder.LAYOUT,
            parent,
            false
        )

        return MarketHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MarketHolder, position: Int) {
        holder.viewDataBinding.also {
            it.market = markets[position]
        }
    }

    override fun getItemCount(): Int {
        return markets.size
    }


}