package com.example.cse438.cse438_assignment2.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistContentRepository(private val songDetailDao : SongDetailDao) {
    val allPlaylists: LiveData<List<PlaylistContent>> = songDetailDao.getAllContents()

    fun insert(content: PlaylistContent){
        CoroutineScope(Dispatchers.IO).launch{
            songDetailDao.insert(content)
        }
    }

    fun clear(){
        CoroutineScope(Dispatchers.IO).launch{
            songDetailDao.deleteAll()
        }
    }

    fun search(id:Int):LiveData<List<PlaylistDisplay>>{
        var list:LiveData<List<PlaylistDisplay>>
        list = songDetailDao.find_details(id)
        return list
    }

    fun remove_song(trackid:Int,playlistid:Int){
        CoroutineScope(Dispatchers.IO).launch{
            songDetailDao.remove_song(trackid,playlistid)
        }
    }
}