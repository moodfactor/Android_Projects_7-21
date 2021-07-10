package com.mood.try1.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mood.try1.domain.Market

@Entity(tableName = "market")
data class MarketDatabase(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imgUrl: String
)

fun List<MarketDatabase>.asDomainModel(): List<Market> {
    return map {
        Market(
            id = it.id,
            title = it.title,
            description = it.description,
            price = it.price,
            imgUrl = it.imgUrl
        )
    }
}