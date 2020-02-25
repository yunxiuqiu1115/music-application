package com.example.cse438.cse438_assignment2.network

import androidx.lifecycle.LiveData
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.SongDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistRepository(private val songDao : SongDao) {
    val allPlaylists: LiveData<List<Playlist>> = songDao.getPlayLists()

    fun insert(playlist:Playlist){
        CoroutineScope(Dispatchers.IO).launch{
            songDao.insert(playlist)
        }
    }

    fun clear(){
        CoroutineScope(Dispatchers.IO).launch{
            songDao.deleteAll()
        }
    }
}