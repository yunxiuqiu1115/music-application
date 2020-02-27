package com.example.cse438.cse438_assignment2.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.PlaylistContent
import kotlinx.android.synthetic.main.dialog_addplaylist.*
import kotlinx.android.synthetic.main.dialog_addplaylist.view.*

class ArchiveActivity : AppCompatActivity() {
    var Playlists:ArrayList<Playlist> = ArrayList()
    var Playlistnames:ArrayList<String> = ArrayList()
    lateinit var viewModel : PlaylistViewModel
    lateinit var viewModel2 : PlaylistContentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)
        viewModel = ViewModelProvider(this).get(PlaylistViewModel::class.java)
        viewModel2 = ViewModelProvider(this).get(PlaylistContentViewModel::class.java)
        dialogView()
    }

    private fun dialogView(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_addplaylist,null)
        // Create a drop-down list for all the current playlists
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Playlistnames)
        // Add song to playlist
        val mBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Please choose the playlist you wanna add")
        val mAlertDialog = mBuilder.show()
            dialogView.playlistSpinner.adapter = adapter
            viewModel!!._playLists.observe(this, Observer{
                Playlists.clear()
                Playlists.addAll(it)
                Playlistnames.clear()
                for(p in Playlists){
                    Playlistnames.add(p.name_of_playlist)
                }
                adapter.notifyDataSetChanged()
            })
            viewModel.getPlayLists()
            mAlertDialog.add_into_playlist.setOnClickListener{
                val track_id = intent!!.getIntExtra("trackId",0)
                val track_name = intent!!.getStringExtra("trackName")
                val artist = intent!!.getStringExtra("artist")
                val duration = intent!!.getIntExtra("duration",0)
                val playlist_id = Playlists[dialogView.playlistSpinner.selectedItemPosition].id
                val c = PlaylistContent(track_id,track_name,artist,duration,playlist_id)
                // Insert into database
                viewModel2.insert(c)
                mAlertDialog.dismiss()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }

    }

}

