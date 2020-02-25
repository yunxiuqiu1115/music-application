package com.example.cse438.trivia.network

import com.example.cse438.cse438_assignment2.network.SongInterface
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    const val BASE_URL = "https://api.deezer.com/"

    fun makeRetrofitService(): SongInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(SongInterface::class.java)
    }
}