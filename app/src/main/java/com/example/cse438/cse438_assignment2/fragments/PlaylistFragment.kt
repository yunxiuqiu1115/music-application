package com.example.cse438.cse438_assignment2.fragments

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
import com.example.cse438.cse438_assignment2.activities.PlaylistViewModel
import com.example.cse438.cse438_assignment2.adapter.PlaylistAdapter
import com.example.cse438.cse438_assignment2.data.Playlist
import kotlinx.android.synthetic.main.fragment_playlists.*
import kotlinx.android.synthetic.main.fragment_playlists.view.*

class PlaylistFragment : Fragment() {
    private lateinit var viewModel : PlaylistViewModel
    private var PlayList: ArrayList<Playlist> = ArrayList()
    private lateinit var addButton : Button
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        addButton = add_button
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlists, container, false)
    }
    
    override fun onViewCreated(view:View, savedInstanceState:Bundle?){
        super.onViewCreated(view,savedInstanceState)
        var adapter = PlaylistAdapter(PlayList)
        playlist_recycler_view.adapter = adapter
        playlist_recycler_view.layoutManager = LinearLayoutManager(this.context)
        playlist_recycler_view.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)

        view.add_button.setOnClickListener{
            val p = Playlist(
                pl_name.text.toString(),
                pl_description.text.toString()
            )
            viewModel!!._playLists.observe(this,Observer{
                PlayList.clear()
                PlayList.addAll(it)
                adapter.notifyDataSetChanged()
            })
            viewModel!!.insert(p)
            val text = "Playlist Added!"
            val duration = Toast.LENGTH_SHORT
            pl_name.setText("")
            pl_description.setText("")
            val toast = Toast.makeText(this.context,text,duration)
            toast.show()
        }
        view.clear_button.setOnClickListener{
            viewModel!!._playLists.observe(this,Observer{
                PlayList.clear()
                PlayList.addAll(it)
                adapter.notifyDataSetChanged()
            })
            viewModel!!.deleteAll()
        }
    }


    override fun onStart(){
        super.onStart()

        var adapter = PlaylistAdapter(PlayList)
        playlist_recycler_view.adapter = adapter
        playlist_recycler_view.layoutManager = LinearLayoutManager(this.context)
        playlist_recycler_view.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        viewModel!!._playLists.observe(this,Observer{playlists->
            PlayList.clear()
            PlayList.addAll(playlists)
            adapter.notifyDataSetChanged()
        })
        
    }
}
