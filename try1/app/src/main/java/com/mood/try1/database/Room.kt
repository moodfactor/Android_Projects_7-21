package com.mood.try1.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MarketDao {

    @Query("SELECT * FROM market")
    fun getGoods(): LiveData<List<MarketDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoods(market: List<MarketDatabase>)
}

@Database(entities = [MarketDatabase::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract val marketDao: MarketDao

}

private lateinit var INSTANCE: MyDatabase

fun getDatabase(context: Context): MyDatabase {
    synchronized(MyDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "markets"
            ).build()
        }
    }

    return INSTANCE
}