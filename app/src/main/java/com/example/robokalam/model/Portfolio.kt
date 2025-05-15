package com.example.robokalam.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class Portfolio(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val college: String,
    val skills: String,
    val projects: Pair<String, String>,
)


class Converters {

    @TypeConverter
    fun fromPair(pair: Pair<String, String>): String {
        return "${pair.first}::${pair.second}"
    }

    @TypeConverter
    fun toPair(data: String): Pair<String, String> {
        val parts = data.split("::")
        return Pair(parts[0], parts.getOrElse(1) { "" })
    }
}


