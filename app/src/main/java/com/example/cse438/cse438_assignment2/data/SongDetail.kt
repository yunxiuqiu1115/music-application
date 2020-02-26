package com.example.cse438.cse438_assignment2.data

data class SongDetail(
    val id:Int,
    val title:String,
    val title_short:String,
    val title_version:String,
    val link: String,
    val duration:Int,
    val rank:Int,
    val release_date:String,
    val gain:Double,
    val explicit_lyrics:Boolean,
    val explicit_content_lyrics: Int,
    val explicit_content_cover:Int,
    val preview:String,
    val track_position:Int,
    val artist:Artist,
    val album:Album,
    val type:String
)