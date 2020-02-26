package com.example.cse438.cse438_assignment2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.activities.DetailsActivity
import com.example.cse438.cse438_assignment2.data.Song
import com.squareup.picasso.Picasso

class SongViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.song_item,parent,false)){
    private val albumImage: ImageView
    private val albumName: TextView

    init{
        albumImage = itemView.findViewById(R.id.image)
        albumName = itemView.findViewById(R.id.album_name)
    }

    fun bind(chartSong: Song){
        albumName?.text = chartSong.title
        Picasso.get().load(chartSong.album.cover_medium).into(albumImage)
        albumImage.setOnClickListener{
            /////////////////////////////
            //Reminder                 //
            //This part is newly added //
            //If the app crashed       //
            //Just remove them         //
            /////////////////////////////

            val intent = Intent(it.getContext(),DetailsActivity::class.java)
            intent.putExtra("id",chartSong.id)
            it.getContext().startActivity(intent)
        }
    }

}

class SongGridAdapter(private val list:ArrayList<Song>)
    : RecyclerView.Adapter<SongViewHolder>(){
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):SongViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return SongViewHolder(inflater,parent)
    }
    override fun onBindViewHolder(holder:SongViewHolder,position:Int){
        val chartSong:Song = list[position]
        holder.bind(chartSong)
    }
    override fun getItemCount():Int = list.size
}

