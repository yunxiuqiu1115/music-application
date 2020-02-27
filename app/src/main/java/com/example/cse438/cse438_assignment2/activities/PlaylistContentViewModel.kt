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
    var _popularsongs : LiveData<List<PopularSong>> = MutableLiveData()
    private val repository : PlaylistContentRepository
    init{
        repository = PlaylistContentRepository(PlaylistRoomDatabase.getDatabase(application).songDetailDao())
        _playlistcontents = repository.allPlaylists
        _playlistdetails = MutableLiveData()
        _popularsongs = repository.popularlist
    }
    // Get all the content
    fun getPlayListContents() : LiveData<List<PlaylistContent>>{
        return _playlistcontents
    }
    // Insert a record
    fun insert(content:PlaylistContent){
        repository.insert(content)
    }
    // Remove all the records
    fun deleteAll(){
        repository.clear()
    }
    // Search details of a playlist by its id
    fun search(id:Int){
        _playlistdetails = repository.search(id)
    }
    // Remove a song
    fun remove_song(trackid:Int,playlistid:Int){
        repository.remove_song(trackid,playlistid)
    }
    // Return a list of songs ordered by its archived times
    fun popularsong():LiveData<List<PopularSong>>{
        return _popularsongs
    }
}