package com.example.cse438.cse438_assignment2.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PopularSong(
    val count:Int,
    val track_id:Int,
    val track_name:String
)
