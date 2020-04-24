package com.jelndoca.spotyapp.listener

import android.os.Bundle
import com.jelndoca.spotyapp.models.AlbumModel

interface AlbumListener {

    fun onClickedAlbum(
        bundle: Bundle?,
        album: AlbumModel
    )

}