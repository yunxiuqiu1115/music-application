package com.example.cse438.cse438_assignment2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongDetailDao {
    @Query("SELECT * FROM playlistcontent")
    fun getAllContents(): LiveData<List<PlaylistContent>>

    @Query("DELETE FROM playlistcontent")
    fun deleteAll()

    @Insert
    fun insert(content:PlaylistContent)

    @Query("SELECT playlistcontent.track_name, playlistcontent.artist, playlists.genre, playlistcontent.duration, playlists.rating "+
    "FROM playlists INNER JOIN playlistcontent " +
    "ON playlists.id = playlistcontent.playlist_id " +
    "And playlists.id =:playlistId")
    fun find_details(playlistId:Int):LiveData<List<PlaylistDisplay>>

}