package com.example.latihan_03_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val player_id: ArrayList<String>            = arrayListOf()
    val player_username: ArrayList<String>      = arrayListOf()
    val player_server: ArrayList<String>        = arrayListOf()
    val player_page: ArrayList<String>          = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simpanDataDiArray()

        val btntambah = findViewById<FloatingActionButton>(R.id.FAB_Add)
        btntambah.setOnClickListener{
            val intentAdmin = Intent(this, TambahPlayerActivity::class.java)
            startActivity(intentAdmin)
        }

        simpanDataDiArray()

        val rv_player = findViewById<RecyclerView>(R.id.rv_server)
        val playerAdapter = PlayerAdapter(this, player_id, player_username, player_server, player_page)
        rv_player.adapter = playerAdapter
        rv_player.layoutManager = LinearLayoutManager(this)
        rv_player.itemAnimator = DefaultItemAnimator()
    }

    fun simpanDataDiArray(){
        val dbAdminServerPlayer   = MyDBHelper(this)
        val dataAdminServerPlayer = dbAdminServerPlayer.bacaSemuaData()

        if (dataAdminServerPlayer.count == 1){
            Toast.makeText(this, "Data tidak ada!", Toast.LENGTH_SHORT).show()
        } else {
            while (dataAdminServerPlayer.moveToNext()){
                player_id.add(dataAdminServerPlayer.getString(0))
                player_username.add(dataAdminServerPlayer.getString(1))
                player_server.add(dataAdminServerPlayer.getString(2))
                player_page.add(dataAdminServerPlayer.getString(3))
            }
        }
    }
}