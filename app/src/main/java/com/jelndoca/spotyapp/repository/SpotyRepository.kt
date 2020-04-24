package com.jelndoca.spotyapp.repository

import com.jelndoca.spotyapp.models.AlbumModel
import com.jelndoca.spotyapp.models.AlbumObjectModel
import com.jelndoca.spotyapp.models.SongModel
import com.jelndoca.spotyapp.models.SongObjectModel
import com.jelndoca.spotyapp.service.ServiceFactory
import com.jelndoca.spotyapp.service.SpotyService
import retrofit2.Call
import java.lang.Exception

class SpotyRepository {
    private var spotyService: SpotyService

    init {
        val serviceFactory = ServiceFactory()
        spotyService = serviceFactory.getInstanceSpotyService()
    }

    fun getAlbums(artist: Int): List<AlbumModel> {
        try {
            val call: Call<List<AlbumObjectModel>> = spotyService.getAlbums(artist)
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()!![0].albums
            } else {
                throw Exception(response.errorBody().toString())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getSongs(album: Int): List<SongModel>{
        try{
            val call: Call<List<SongObjectModel>> = spotyService.getSongs(album)
            val response = call.execute()
            if(response.isSuccessful){
                return response.body()!![0].songs
            } else{
                throw Exception(response.errorBody().toString())
            }
        }catch (e: Exception){
            throw e;
        }
    }
}