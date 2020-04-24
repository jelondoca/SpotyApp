package com.jelndoca.spotyapp.listener

import android.os.Bundle
import com.jelndoca.spotyapp.models.SongModel

interface SongListener {

    fun onClickedSong(
        song: SongModel
    )

}