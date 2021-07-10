package com.mood.try1.network

import androidx.room.ColumnInfo
import com.mood.try1.database.MarketDatabase
import com.mood.try1.domain.Market
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarketNetworkContainer(val markets: List<MarketNetwork>)

@JsonClass(generateAdapter = true)
data class MarketNetwork(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imgUrl: String
)

/**
 * Convert Network results to domain objects
 */
fun MarketNetworkContainer.asDomainModel(): List<Market> {
    return markets.map {
        Market(
            id = it.id,
            title = it.title,
            description = it.description,
            price = it.price,
            imgUrl = it.imgUrl
        )
    }
}

/**
 * Convert Network results to database objects
 */
fun MarketNetworkContainer.asDatabaseModel(): List<MarketDatabase> {
    return markets.map {
        MarketDatabase(
            id = it.id,
            title = it.title,
            description = it.description,
            price = it.price,
            imgUrl = it.imgUrl
        )
    }
}
