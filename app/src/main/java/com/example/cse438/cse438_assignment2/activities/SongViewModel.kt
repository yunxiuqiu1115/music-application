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
    public var chartList: MutableLiveData<TrackWrapper> = MutableLiveData()
    public var songRepository : SongRepository = SongRepository()


    //NewlyAdded
    public var searchList: MutableLiveData<ChartPayload> = MutableLiveData()

    public var songDetail: MutableLiveData<SongDetail> = MutableLiveData()

    fun getChart(){
        songRepository.getChart(chartList)
    }
    //NewlyAdded
    fun getSongByKeyWord(q:String){
        songRepository.getSongByKeyWord(searchList,q)
    }

    fun getSongById(trackId:Int){
        songRepository.getSongById(songDetail,trackId)
    }
}