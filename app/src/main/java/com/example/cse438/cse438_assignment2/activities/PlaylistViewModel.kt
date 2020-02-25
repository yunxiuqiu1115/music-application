package com.example.cse438.cse438_assignment2.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.data.ChartPayload
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.PlaylistRoomDatabase
import com.example.cse438.cse438_assignment2.data.TrackWrapper
import com.example.cse438.cse438_assignment2.network.PlaylistRepository
import com.example.cse438.cse438_assignment2.network.SongRepository

class PlaylistViewModel(application: Application): AndroidViewModel(application) {
    var _playLists : LiveData<List<Playlist>> = MutableLiveData()
    private val repository : PlaylistRepository
    init{
        repository = PlaylistRepository(PlaylistRoomDatabase.getDatabase(application).songDao())
        _playLists = repository.allPlaylists
    }

    fun getPlayLists() : LiveData<List<Playlist>>{
        return _playLists
    }

    fun insert(playlist:Playlist){
        repository.insert(playlist)
    }

    fun deleteAll(){
        repository.clear()
    }
}