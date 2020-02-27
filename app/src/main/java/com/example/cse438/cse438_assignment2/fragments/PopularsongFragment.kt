package com.example.cse438.cse438_assignment2.fragments

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.activities.PlaylistContentViewModel
import com.example.cse438.cse438_assignment2.activities.PlaylistViewModel
import com.example.cse438.cse438_assignment2.adapter.PlaylistAdapter
import com.example.cse438.cse438_assignment2.adapter.PopularsongAdapter
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.PopularSong
import kotlinx.android.synthetic.main.enter_playlist.*
import kotlinx.android.synthetic.main.enter_playlist.view.*
import kotlinx.android.synthetic.main.fragment_playlists.*
import kotlinx.android.synthetic.main.fragment_playlists.view.*
import kotlinx.android.synthetic.main.fragment_popularsong.*

class PopularsongFragment : Fragment() {
    private lateinit var viewModel : PlaylistContentViewModel
    private var Popularlist: ArrayList<PopularSong> = ArrayList()
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popularsong, container, false)
    }
    
    override fun onViewCreated(view:View, savedInstanceState:Bundle?){
        super.onViewCreated(view,savedInstanceState)
        var adapter = PopularsongAdapter(Popularlist)
        popular_recycler_view.adapter = adapter
        popular_recycler_view.layoutManager = LinearLayoutManager(this.context)
        popular_recycler_view.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(PlaylistContentViewModel::class.java)
    }


    override fun onStart(){
        super.onStart()
        // Get the popular songs and display them
        var adapter = PopularsongAdapter(Popularlist)
        popular_recycler_view.adapter = adapter
        popular_recycler_view.layoutManager = LinearLayoutManager(this.context)
        popular_recycler_view.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(PlaylistContentViewModel::class.java)
        viewModel!!._popularsongs.observe(this,Observer{it
            Popularlist.clear()
            Popularlist.addAll(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.popularsong()
        
    }
}
