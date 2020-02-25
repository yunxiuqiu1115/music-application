package com.example.cse438.cse438_assignment2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.Song
import com.squareup.picasso.Picasso

class PlaylistViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_item,parent,false)){
    private val playlistName: TextView
    private val playlistDescription: TextView

    init{
        playlistName = itemView.findViewById(R.id.playlist_name)
        playlistDescription = itemView.findViewById(R.id.playlist_description)
    }

    fun bind(playlist: Playlist){
        playlistName.text = playlist.name_of_playlist
        playlistDescription.text = playlist.description
    }

}

class PlaylistAdapter(private val list:ArrayList<Playlist>)
    : RecyclerView.Adapter<PlaylistViewHolder>(){
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):PlaylistViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return PlaylistViewHolder(inflater,parent)
    }
    override fun onBindViewHolder(holder:PlaylistViewHolder,position:Int){
        val playlist:Playlist = list[position]
        holder.bind(playlist)
    }
    override fun getItemCount():Int = list.size
}