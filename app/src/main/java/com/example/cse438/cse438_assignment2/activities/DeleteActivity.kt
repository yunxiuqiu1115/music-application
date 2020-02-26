package com.example.cse438.cse438_assignment2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.adapter.DetailAdapter
import kotlinx.android.synthetic.main.activity_details.*

class DeleteActivity : AppCompatActivity() {
    private lateinit var viewModel : PlaylistContentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)
        val trackId = intent!!.getIntExtra("trackid",0)
        val playlistId = intent!!.getIntExtra("playlistid",0)
        viewModel = ViewModelProvider(this).get(PlaylistContentViewModel::class.java)
        viewModel.remove_song(trackId,playlistId)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("tab",1)
        startActivity(intent)
    }
}
