package com.example.cse438.cse438_assignment2.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.data.ChartPayload
import com.example.cse438.cse438_assignment2.data.Song
import com.example.cse438.cse438_assignment2.data.SongDetail
import com.example.cse438.cse438_assignment2.data.TrackWrapper
import com.example.cse438.cse438_assignment2.network.SongRepository

class SongViewModel(application: Application): AndroidViewModel(application) {
    var chartList: MutableLiveData<TrackWrapper> = MutableLiveData()
    var songRepository : SongRepository = SongRepository()
    var searchList: MutableLiveData<ChartPayload> = MutableLiveData()
    var songDetail: MutableLiveData<SongDetail> = MutableLiveData()
    // Get the songs from the chart
    fun getChart(){
        songRepository.getChart(chartList)
    }
    // Search a song by keyword
    fun getSongByKeyWord(q:String){
        songRepository.getSongByKeyWord(searchList,q)
    }
    // Get the details of a song by its id
    fun getSongById(trackId:Int){
        songRepository.getSongById(songDetail,trackId)
    }
}