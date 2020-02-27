package com.example.cse438.cse438_assignment2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongDao {
    @Query("SELECT * FROM playlists")
    fun getPlayLists(): LiveData<List<Playlist>>

    @Query("DELETE FROM playlists")
    fun deleteAll()

    @Insert
    fun insert(playlist:Playlist)
}