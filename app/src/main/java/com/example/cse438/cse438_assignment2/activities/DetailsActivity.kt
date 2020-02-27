package com.example.cse438.cse438_assignment2.activities

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.adapter.DetailAdapter
import com.example.cse438.cse438_assignment2.adapter.SongGridAdapter
import com.example.cse438.cse438_assignment2.data.Song
import com.example.cse438.cse438_assignment2.data.SongDetail
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import retrofit2.http.Url

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel : SongViewModel
//    private lateinit var searchButton : Button
//    private lateinit var searchContent : TextView
    var detailList: ArrayList<SongDetail> = ArrayList()
    var url = ""
    lateinit var btnPause:Button
    lateinit var btnPlay:Button
    lateinit var backButton:Button
    lateinit var mediaPlayer:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        // Get the id from previous click
        val trackId = intent!!.getIntExtra("id",0)
        var adapter = DetailAdapter(detailList)
        detail_recycler_view.adapter = adapter
        detail_recycler_view.layoutManager = LinearLayoutManager(this)
        detail_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(SongViewModel::class.java)
        viewModel!!.songDetail.observe(this, Observer{
            detailList.clear()
            detailList.add(it)
            url = it.preview
            adapter.notifyDataSetChanged()})
        // Get song's details from track API by its id
        viewModel.getSongById(trackId)
        btnPlay = findViewById(R.id.btnPlay)
        btnPause = findViewById(R.id.btnPause)
        backButton = findViewById(R.id.back_button)
        // Make a player to hear the snippet
        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener{
            mediaPlayer.start()
        }
        mediaPlayer.setOnErrorListener(object : MediaPlayer.OnErrorListener{
            override fun onError(p0:MediaPlayer?,p1:Int,p2:Int) : Boolean{
                mediaPlayer.reset()
                return false
            }
        })
        // Playing music
        btnPlay.setOnClickListener{
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()
        }
        // Pause music
        btnPause.setOnClickListener{
            mediaPlayer.reset()
        }
        // Go back to main page
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
