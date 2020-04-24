package com.jelndoca.spotyapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jelndoca.spotyapp.adapters.SongAdapter
import com.jelndoca.spotyapp.listener.SongListener
import com.jelndoca.spotyapp.models.AlbumModel
import com.jelndoca.spotyapp.models.SongModel
import com.jelndoca.spotyapp.repository.SpotyRepository
import com.jelndoca.spotyapp.utils.ITEM_ALBUM
import com.jelndoca.spotyapp.utils.ValidateInternet
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity(), SongListener {

    val validateInternet = ValidateInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        intent?.let { myIntent ->
            val album = myIntent.getParcelableExtra<AlbumModel>(ITEM_ALBUM)
            if(album != null) {
                Glide.with(this).load(album.image).into(imgHeaderDetail)
                txtTitleDeail.text = album.name
                createThreadToGetSongs(album.id)
            }else{
                backToAlbum()
            }
        }
    }

    private fun backToAlbum() {
        val intent = Intent(this, AlbumActivity::class.java)
        startActivity(intent)
    }

    override fun onClickedSong(song: SongModel) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(song.url))
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    private fun createThreadToGetSongs(idAlbum: Int) {
        val thread = Thread(Runnable {
            getSongsFromRepository(idAlbum)
        })
        thread.start()
    }

    fun getSongsFromRepository(idAlbum: Int) {
        try {
            val repository = SpotyRepository()
            val result = repository.getSongs(idAlbum)
            loadAdapter(result)
        } catch (e: Exception) {
            runOnUiThread {
                Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun loadAdapter(result: List<SongModel>) {
        runOnUiThread {
            rvSong.layoutManager = LinearLayoutManager(this)
            rvSong.adapter = SongAdapter(result, this)
        }
    }
}
