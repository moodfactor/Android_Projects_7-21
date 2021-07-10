package com.mood.try1.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val URL = "https://api.jsonbin.io/b/60e875bcf72d2b70bbac7a77/"

interface MarketService{

    @GET(value = "3")
    suspend fun getMarketList(): MarketNetworkContainer
}

object MarketServiceObject{

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val markets: MarketService = retrofit.create(MarketService::class.java)
}