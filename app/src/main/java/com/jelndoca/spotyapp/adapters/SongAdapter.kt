package com.jelndoca.spotyapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jelndoca.spotyapp.R
import com.jelndoca.spotyapp.holders.SongHolder
import com.jelndoca.spotyapp.listener.SongListener
import com.jelndoca.spotyapp.models.SongModel

class SongAdapter(private val data: List<SongModel>, val listener: SongListener) : RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bindSong(data[position], listener)
    }

}