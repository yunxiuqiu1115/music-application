package com.example.cse438.cse438_assignment2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="playlistcontent")
data class PlaylistContent (
    @ColumnInfo(name="song_id")
    val song_id:Int
)
{
    @PrimaryKey(autoGenerate=true)
    var id:Int = 0
}