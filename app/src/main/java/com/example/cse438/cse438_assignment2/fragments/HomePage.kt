package com.example.cse438.cse438_assignment2.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.activities.SongViewModel
import com.example.cse438.cse438_assignment2.adapter.SongGridAdapter
import com.example.cse438.cse438_assignment2.data.Song
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePage : Fragment() {

    private lateinit var viewModel : SongViewModel
    private lateinit var searchButton : Button
    private lateinit var searchContent : TextView
    var chartList: ArrayList<Song> = ArrayList()
    var searchList: ArrayList<Song> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onStart(){
        super.onStart()

        var adapter = SongGridAdapter(chartList)
        searchButton = search_button
        searchContent = keyword
        song_recycler_view.adapter = adapter
//        song_recycler_view.layoutManager = LinearLayoutManager(this.context)
        song_recycler_view.setLayoutManager(GridLayoutManager(this.context,2))
        song_recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(SongViewModel::class.java)

        viewModel!!.chartList.observe(this, Observer{
            chartList.clear()
            chartList.addAll(it.tracks.data)
            adapter.notifyDataSetChanged()})

        viewModel.getChart()

        searchButton.setOnClickListener{
            // Vanitize the input: search keyword should not be null
            val searchcontent = searchContent.text.toString()
            if(searchcontent==null||searchcontent==""){
                val myToast = Toast.makeText(it.context,"Please enter the search keyword",Toast.LENGTH_SHORT)
                myToast.show()
            }
            else{
                var adapter2 = SongGridAdapter(searchList)
                song_recycler_view.adapter = adapter2
                song_recycler_view.layoutManager = LinearLayoutManager(this.context)
                song_recycler_view.setLayoutManager(GridLayoutManager(this.context,2))
                song_recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                viewModel!!.searchList.observe(this,Observer{
                    searchList.clear()
                    searchList.addAll(it.data)
                    adapter2.notifyDataSetChanged()
                })
                viewModel.getSongByKeyWord(searchContent.text.toString())
            }
        }

     }

}
