package com.example.cse438.cse438_assignment2.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="playlistcontent")
data class PlaylistContent (

    @ColumnInfo(name="track_id")
    val track_id:Int,

    @ColumnInfo(name="track_name")
    val track_name:String,

    @ColumnInfo(name="artist")
    val artist:String,

    @ColumnInfo(name="duration")
    val duration:Int,

    @ColumnInfo(name="playlist_id")
    val playlist_id:Int
)
{
    @PrimaryKey(autoGenerate=true)
    var id:Int = 0
}