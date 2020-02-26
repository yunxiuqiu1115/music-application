package com.example.cse438.cse438_assignment2.activities

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.data.*
import com.example.cse438.cse438_assignment2.network.PlaylistContentRepository
import com.example.cse438.cse438_assignment2.network.PlaylistRepository
import com.example.cse438.cse438_assignment2.network.SongRepository

class PlaylistContentViewModel(application: Application): AndroidViewModel(application) {
    var _playlistcontents : LiveData<List<PlaylistContent>> = MutableLiveData()
    var _playlistdetails : LiveData<List<PlaylistDisplay>> = MutableLiveData()
    private val repository : PlaylistContentRepository
    init{
        repository = PlaylistContentRepository(PlaylistRoomDatabase.getDatabase(application).songDetailDao())
        _playlistcontents = repository.allPlaylists
        _playlistdetails = repository.search(1)
    }

    fun getPlayListContents() : LiveData<List<PlaylistContent>>{
        return _playlistcontents
    }

    fun insert(content:PlaylistContent){
        repository.insert(content)
    }

    fun deleteAll(){
        repository.clear()
    }

    fun search(id:Int) : LiveData<List<PlaylistDisplay>>{
        Log.d("debuggg","debug viewmodel")
        _playlistdetails = repository.search(id)
        return _playlistdetails
    }
}