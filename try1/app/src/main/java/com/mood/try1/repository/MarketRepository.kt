package com.mood.try1.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mood.try1.database.MyDatabase
import com.mood.try1.database.asDomainModel
import com.mood.try1.domain.Market
import com.mood.try1.network.MarketServiceObject
import com.mood.try1.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MarketRepository(val database: MyDatabase) {

    val markets: LiveData<List<Market>> = Transformations.map(database.marketDao.getGoods()) {
        it.asDomainModel()
    }

    suspend fun refreshMarkets() {
        withContext(Dispatchers.IO) {
            Timber.d("refreshMarkets is called")
            val marketList = MarketServiceObject.markets.getMarketList()
            database.marketDao.insertGoods(marketList.asDatabaseModel())
        }
    }
}