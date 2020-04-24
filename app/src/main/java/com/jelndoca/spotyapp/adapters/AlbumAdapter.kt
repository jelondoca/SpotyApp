package com.jelndoca.spotyapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jelndoca.spotyapp.R
import com.jelndoca.spotyapp.holders.AlbumHolder
import com.jelndoca.spotyapp.listener.AlbumListener
import com.jelndoca.spotyapp.models.AlbumModel

class AlbumAdapter(val data: List<AlbumModel>, val listener: AlbumListener): RecyclerView.Adapter<AlbumHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        return AlbumHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bindAlbum(data[position], listener)
    }
}