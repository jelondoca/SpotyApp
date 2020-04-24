package com.jelndoca.spotyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.jelndoca.spotyapp.adapters.AlbumAdapter
import com.jelndoca.spotyapp.listener.AlbumListener
import com.jelndoca.spotyapp.models.AlbumModel
import com.jelndoca.spotyapp.repository.SpotyRepository
import com.jelndoca.spotyapp.utils.ITEM_ALBUM
import com.jelndoca.spotyapp.utils.ValidateInternet
import kotlinx.android.synthetic.main.activity_album.*
import java.lang.Exception

class AlbumActivity : AppCompatActivity(), AlbumListener {

    val validateInternet = ValidateInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        validateInternetToGetAlbum()

    }

    private fun validateInternetToGetAlbum() {
        if (validateInternet.isInternetAvailable(this)) {
            createThreadToGetAlbums()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(R.string.error_internet)
                .setPositiveButton("Reintentar") { _, _ ->
                    validateInternetToGetAlbum()
                }
                .setNegativeButton("Salir") { _, _ ->
                    finish()
                }
                .create()
                .show()
        }
    }

    override fun onClickedAlbum(bundle: Bundle?, album: AlbumModel) {
        val intent = Intent(this, SongActivity::class.java)
        intent.putExtra(ITEM_ALBUM, album)
        startActivity(intent, bundle)
    }

    private fun createThreadToGetAlbums() {
        val thread = Thread(Runnable {
            getAlbumFromRepository()
        })
        thread.start()
    }

    private fun getAlbumFromRepository() {
        try {
            val repository = SpotyRepository()
            val result = repository.getAlbums(2)
            loadAdapter(result)
        } catch (e: Exception) {
            runOnUiThread {
                Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadAdapter(result: List<AlbumModel>) {
        runOnUiThread {
            recyclerViewAlbum.layoutManager = GridLayoutManager(this, 2)
            recyclerViewAlbum.adapter = AlbumAdapter(result, this)
        }
    }


}
