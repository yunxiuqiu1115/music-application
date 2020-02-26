package com.example.cse438.cse438_assignment2.data

import androidx.room.Ignore

data class PlaylistDisplay(
    val track_name:String,
    val artist:String,
    val genre:String,
    val duration:Int,
    val rating:Int
)