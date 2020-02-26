package com.example.cse438.cse438_assignment2.activities

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

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel : SongViewModel
//    private lateinit var searchButton : Button
//    private lateinit var searchContent : TextView
    var detailList: ArrayList<SongDetail> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val trackId = intent!!.getIntExtra("id",0)
        var adapter = DetailAdapter(detailList)
        detail_recycler_view.adapter = adapter
        detail_recycler_view.layoutManager = LinearLayoutManager(this)
        detail_recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(SongViewModel::class.java)
        viewModel!!.songDetail.observe(this, Observer{
            detailList.clear()
            detailList.add(it)
            adapter.notifyDataSetChanged()})

        viewModel.getSongById(trackId)
    }

//    override fun onStart(){
//        super.onStart()
//
//
//    }
}
