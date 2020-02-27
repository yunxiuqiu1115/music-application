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

    @Query("SELECT playlistcontent.track_name, playlistcontent.track_id, playlistcontent.playlist_id, playlistcontent.artist, playlists.genre, playlistcontent.duration, playlists.rating "+
    "FROM playlists INNER JOIN playlistcontent " +
    "ON playlists.id = playlistcontent.playlist_id " +
    "And playlists.id =:playlistId")
    fun find_details(playlistId:Int):LiveData<List<PlaylistDisplay>>

    @Query("DELETE FROM playlistcontent "+
    "WHERE track_id =:trackid "+
    "AND playlist_id =:playlistid")
    fun remove_song(trackid:Int,playlistid:Int)

    @Query("SELECT COUNT(*) AS count,track_id,track_name "+
    "FROM playlistcontent "+
    "GROUP BY track_id "+
    "ORDER BY COUNT(*) DESC")
    fun popularsong() : LiveData<List<PopularSong>>

}