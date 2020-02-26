package com.example.cse438.cse438_assignment2.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.activities.ArchiveActivity
import com.example.cse438.cse438_assignment2.activities.DetailsActivity
import com.example.cse438.cse438_assignment2.activities.MainActivity
import com.example.cse438.cse438_assignment2.activities.PlaylistViewModel
import com.example.cse438.cse438_assignment2.data.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.enter_playlist.*
import kotlinx.android.synthetic.main.enter_playlist.view.*
import kotlinx.android.synthetic.main.dialog_addplaylist.*
import kotlinx.android.synthetic.main.dialog_addplaylist.view.*

class DetailViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.detail_item,parent,false)){
    private val trackName: TextView
    private val trackImage: ImageView
    private val albumName: TextView
    private val trackPosition: TextView
    private val trackLength: TextView
    private val releaseDate:TextView
    private val trackRank:TextView
    private val trackGain:TextView
    private var addToPlayList : Button
//    private var backButton : Button
    lateinit var viewModel : PlaylistViewModel
    private var Playlistnames:ArrayList<String> = ArrayList()
    private var Playlists:ArrayList<Playlist> = ArrayList()
    init{
        trackName = itemView.findViewById(R.id.track_name)
        trackImage = itemView.findViewById(R.id.track_image)
        albumName = itemView.findViewById(R.id.album_name)
        trackPosition = itemView.findViewById(R.id.track_position)
        trackLength = itemView.findViewById(R.id.track_length)
        releaseDate = itemView.findViewById(R.id.release_date)
        trackRank = itemView.findViewById(R.id.track_rank)
        trackGain = itemView.findViewById(R.id.track_gain)
        addToPlayList = itemView.findViewById(R.id.add_to_playlist)
//        backButton = findViewById(R.id.back_button)


    }

    fun bind(chartSong: SongDetail){
        trackName?.text = chartSong.title
        Picasso.get().load(chartSong.album.cover_medium).into(trackImage)
        albumName?.text = chartSong.album.title
        trackPosition?.text = chartSong.track_position.toString()
        trackLength?.text = chartSong.duration.toString()
        releaseDate?.text = chartSong.release_date
        trackRank?.text = chartSong.rank.toString()
        trackGain?.text = chartSong.gain.toString()
        addToPlayList?.text = "ADD TO PLAYLIST"

        addToPlayList.setOnClickListener{
            val intent = Intent(it.getContext(), ArchiveActivity::class.java)
            intent.putExtra("trackId",chartSong.id)
            intent.putExtra("trackName",chartSong.title)
            intent.putExtra("artist",chartSong.artist.name)
            intent.putExtra("duration",chartSong.duration)
            it.getContext().startActivity(intent)
        }


    }

}

class DetailAdapter(private val list:ArrayList<SongDetail>)
    : RecyclerView.Adapter<DetailViewHolder>(){
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):DetailViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return DetailViewHolder(inflater,parent)
    }
    override fun onBindViewHolder(holder:DetailViewHolder,position:Int){
        val chartSong:SongDetail = list[position]
        holder.bind(chartSong)

    }
    override fun getItemCount():Int = list.size
}

