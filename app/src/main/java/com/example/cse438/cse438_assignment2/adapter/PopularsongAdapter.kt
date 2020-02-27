package com.example.cse438.cse438_assignment2.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.activities.DetailsActivity
import com.example.cse438.cse438_assignment2.activities.PlaylistActivity
import com.example.cse438.cse438_assignment2.data.Playlist
import com.example.cse438.cse438_assignment2.data.PopularSong
import com.example.cse438.cse438_assignment2.data.Song
import com.squareup.picasso.Picasso

class PopularsongViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.popularsong_item,parent,false)){
    private val popularName: TextView
    private val popularCount: TextView

    init{
        popularName = itemView.findViewById(R.id.popular_name)
        popularCount = itemView.findViewById(R.id.popular_count)
    }

    fun bind(popularsong: PopularSong,place:Int){
        // Top1 popular song marked with golden
        if(place==0){
            popularName.setTextColor(Color.parseColor("#ffdf00"))
        }
        // Top2 Silver
        else if(place==1){
            popularName.setTextColor(Color.parseColor("#C0C0C0"))
        }
        // Top3 Bronze
        else if(place==2){
            popularName.setTextColor(Color.parseColor("#CD7F32"))
        }
        popularName.text = popularsong.track_name
        popularCount.text = "Archived times: " + popularsong.count.toString()

    }

}

class PopularsongAdapter(private val list:ArrayList<PopularSong>)
    : RecyclerView.Adapter<PopularsongViewHolder>(){
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):PopularsongViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return PopularsongViewHolder(inflater,parent)
    }
    override fun onBindViewHolder(holder:PopularsongViewHolder,position:Int){
        val popularsong:PopularSong = list[position]
        val place = position
        holder.bind(popularsong,place)
    }
    override fun getItemCount():Int = list.size
}