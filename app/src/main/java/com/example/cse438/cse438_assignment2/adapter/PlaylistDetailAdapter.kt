package com.example.cse438.cse438_assignment2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.activities.DeleteActivity
import com.example.cse438.cse438_assignment2.activities.DetailsActivity
import com.example.cse438.cse438_assignment2.activities.PlaylistActivity
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.PlaylistDisplay
import com.example.cse438.cse438_assignment2.data.Song
import com.squareup.picasso.Picasso

class PlaylistDetailViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.playlist_detail_item,parent,false)){
    private val plTrackName: TextView
    private val plArtist: TextView
    private val plGenre: TextView
    private val plDuration :TextView
    private val plRating : TextView
    private val deleteButton : Button
    init{
        plTrackName = itemView.findViewById(R.id.pl_trackname)
        plArtist = itemView.findViewById(R.id.pl_artist)
        plGenre = itemView.findViewById(R.id.pl_genre)
        plDuration = itemView.findViewById(R.id.pl_duration)
        plRating = itemView.findViewById(R.id.pl_rating)
        deleteButton = itemView.findViewById(R.id.delete_button)
    }

    fun bind(display: PlaylistDisplay){
        plTrackName.text = display.track_name
        plArtist.text = display.artist
        plGenre.text = display.genre
        plDuration.text = display.duration.toString()
        plRating.text = display.rating.toString()
        // Remove a song from a playlist
        deleteButton.setOnClickListener{
            val intent = Intent(it.getContext(), DeleteActivity::class.java)
            intent.putExtra("trackid",display.track_id)
            intent.putExtra("playlistid",display.playlist_id)
            it.getContext().startActivity(intent)
        }
    }

}

class PlaylistDetailAdapter(private val list:ArrayList<PlaylistDisplay>)
    : RecyclerView.Adapter<PlaylistDetailViewHolder>(){
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):PlaylistDetailViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return PlaylistDetailViewHolder(inflater,parent)
    }
    override fun onBindViewHolder(holder:PlaylistDetailViewHolder,position:Int){
        val display:PlaylistDisplay = list[position]
        holder.bind(display)
    }
    override fun getItemCount():Int = list.size
}