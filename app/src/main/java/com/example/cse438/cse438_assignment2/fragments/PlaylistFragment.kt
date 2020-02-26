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
import com.example.cse438.cse438_assignment2.activities.PlaylistViewModel
import com.example.cse438.cse438_assignment2.adapter.PlaylistAdapter
import com.example.cse438.cse438_assignment2.data.Playlist
import kotlinx.android.synthetic.main.enter_playlist.*
import kotlinx.android.synthetic.main.enter_playlist.view.*
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
            val dialogView = LayoutInflater.from(this.context).inflate(R.layout.enter_playlist,null)
            val mBuilder = AlertDialog.Builder(this.context)
                .setView(dialogView)
                .setTitle("Enter the new playlist")
            val mAlertDialog = mBuilder.show()

            mAlertDialog.submitPlaylist.setOnClickListener{
                val plName = dialogView.pl_name.text.toString()
                val plDes = dialogView.pl_description.text.toString()
                val plRate = dialogView.pl_rate.text.toString().toIntOrNull()
                val plGenre = dialogView.pl_genre.text.toString()
                if(plName==null||plName==""||plDes==""||plDes==null||plGenre==null||plGenre==""){
                    val myToast = Toast.makeText(it.context,"Please fill all the blanks",Toast.LENGTH_SHORT)
                    myToast.show()
                }
                else if(plRate==null||plRate<0||plRate>10){
                    val myToast = Toast.makeText(it.context,"Please enter valid rating",Toast.LENGTH_SHORT)
                    myToast.show()
                }
                else{
                    val p = Playlist(
                        plName,
                        plDes,
                        plRate,
                        plGenre
                    )

                viewModel!!._playLists.observe(this,Observer{
                    PlayList.clear()
                    PlayList.addAll(it)
                    adapter.notifyDataSetChanged()
                    })
                    viewModel!!.insert(p)
                    val text = "Playlist Added!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this.context,text,duration)
                    toast.show()
                    mAlertDialog.dismiss()
                }
            }
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
