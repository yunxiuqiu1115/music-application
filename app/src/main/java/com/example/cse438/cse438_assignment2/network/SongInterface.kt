package com.example.cse438.cse438_assignment2.network

import com.example.cse438.cse438_assignment2.data.ChartPayload
import com.example.cse438.cse438_assignment2.data.Song
import com.example.cse438.cse438_assignment2.data.TrackWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongInterface {

    @GET("chart/tracks")
    suspend fun getChart(): Response<TrackWrapper>

    @GET("search")
    suspend fun getSongByKeyWord(@Query("q") q:String) : Response<ChartPayload>

}