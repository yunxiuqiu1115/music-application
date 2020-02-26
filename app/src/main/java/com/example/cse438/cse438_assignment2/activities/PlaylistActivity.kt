package com.example.cse438.cse438_assignment2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.adapter.DetailAdapter
import com.example.cse438.cse438_assignment2.adapter.PlaylistDetailAdapter
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.PlaylistContent
import com.example.cse438.cse438_assignment2.data.PlaylistDisplay
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_playlist.*

class PlaylistActivity : AppCompatActivity() {
    var results:ArrayList<PlaylistDisplay> = ArrayList()
    var test:ArrayList<PlaylistContent> = ArrayList()
    lateinit var viewModel : PlaylistContentViewModel
    lateinit var viewModel2 : PlaylistContentViewModel
    var playlistId  = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        playlistId = intent!!.getIntExtra("id",1)
        Log.d("debuggg ","id "+playlistId)
    }
    override fun onStart(){
        super.onStart()
        var adapter = PlaylistDetailAdapter(results)
        playlistdetail_recycler_view.adapter = adapter
        playlistdetail_recycler_view.layoutManager = LinearLayoutManager(this)
        playlistdetail_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(PlaylistContentViewModel::class.java)
        viewModel!!._playlistdetails.observe(this, Observer{

            results.clear()
            results.addAll(it)
            Log.d("debuggg","See if you can reach here"+results.size.toString())
            adapter.notifyDataSetChanged()})

        viewModel.search(playlistId)
    }
}
