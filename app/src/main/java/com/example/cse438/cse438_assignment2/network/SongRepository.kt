package com.example.cse438.cse438_assignment2.network

import androidx.lifecycle.MutableLiveData
import com.example.cse438.cse438_assignment2.data.ChartPayload
import com.example.cse438.cse438_assignment2.data.TrackWrapper
import com.example.cse438.trivia.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SongRepository {
    val service = ApiClient.makeRetrofitService()
    fun getChart(resBody: MutableLiveData<TrackWrapper>){
        CoroutineScope(Dispatchers.IO).launch{
            val response = service.getChart()
            withContext(Dispatchers.Main){
                try{
                    if(response.isSuccessful){
                        resBody.value = response.body()
                    }
                } catch(e: HttpException){
                    println("Http error")
                }
            }
        }
    }
    fun getSongByKeyWord(resBody:MutableLiveData<ChartPayload>,q:String){
        CoroutineScope(Dispatchers.IO).launch{
            val response = service.getSongByKeyWord(q)

            withContext(Dispatchers.Main){
                try{
                    if(response.isSuccessful){
                        resBody.value = response.body()
                    }
                } catch(e:HttpException){
                    println("Http error")
                }
            }
        }
    }
}