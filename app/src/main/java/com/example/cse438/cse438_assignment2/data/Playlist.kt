package com.example.cse438.cse438_assignment2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="playlists")
data class Playlist (
    @ColumnInfo(name="name_of_playlist")
    val name_of_playlist:String,

    @ColumnInfo(name="description")
    val description:String
)
{
    @PrimaryKey(autoGenerate=true)
    var id:Int = 0
}