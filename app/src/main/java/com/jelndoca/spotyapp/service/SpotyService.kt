package com.jelndoca.spotyapp.service

import com.jelndoca.spotyapp.models.AlbumModel
import com.jelndoca.spotyapp.models.AlbumObjectModel
import com.jelndoca.spotyapp.models.ArtistsObjectModel
import com.jelndoca.spotyapp.models.SongObjectModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotyService {

    @GET("artists")
    fun getArtists():Call<List<ArtistsObjectModel>>

    @GET("albums")
    fun getAlbums(@Query("artist") value: Int):Call<List<AlbumObjectModel>>

    @GET("songs")
    fun getSongs(@Query("album") value: Int): Call<List<SongObjectModel>>
}
