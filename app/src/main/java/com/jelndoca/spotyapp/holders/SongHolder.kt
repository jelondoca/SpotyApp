package com.jelndoca.spotyapp.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jelndoca.spotyapp.listener.SongListener
import com.jelndoca.spotyapp.models.SongModel
import kotlinx.android.synthetic.main.item_song.view.*

class SongHolder(private val view: View): RecyclerView.ViewHolder(view) {
    fun bindSong(itemSongModel: SongModel, listener: SongListener){
        view.txtTitleSong.text = itemSongModel.name
        view.txtDurationSong.text = calculateTime(itemSongModel.time)
        view.setOnClickListener {
            listener.onClickedSong(itemSongModel)
        }
    }

    private fun calculateTime(duration: String): String{
        val minutes = duration.toInt()/1000/60
        val seconds = duration.toInt()/1000 % 60
        return "$minutes:${if(seconds <10) "0$seconds" else seconds}"
    }
}