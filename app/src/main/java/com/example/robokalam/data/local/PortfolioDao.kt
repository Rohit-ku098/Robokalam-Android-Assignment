package com.example.robokalam.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.robokalam.model.Portfolio
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolio(portfolio: Portfolio)

    @Update
    suspend fun updatePortfolio(portfolio: Portfolio)

    @Query("SELECT * FROM portfolio")
    fun getAllPortfolios(): Flow<List<Portfolio>>

}