package com.example.latihan_03_sqlite

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class UbahplayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubahplayer)

        val actionbar = supportActionBar
        if (intent.hasExtra("username")){
            actionbar?.title = intent.getStringExtra("username")
        }

        val btnUbah = findViewById<Button>(R.id.btn_ubah)

        getIntentData()

        btnUbah.setOnClickListener {
            val dbAdmin = MyDBHelper(this)

            val idPlayer = intent.getStringExtra("id")
            val usernamePlayer = findViewById<EditText>(R.id.txt_edit_username).text.toString()
            val serverPlayer = findViewById<EditText>(R.id.txt_edit_server).text.toString()
            val pagePlayer = findViewById<EditText>(R.id.txt_edit_page).text.toString()

            dbAdmin.ubahPlayer(idPlayer, usernamePlayer, serverPlayer, pagePlayer)
        }

        val btnDelete = findViewById<Button>(R.id.btn_hapus)
        btnDelete.setOnClickListener {
            dialogKonfirmasi()
        }
    }

    fun getIntentData(){
        if(
            intent.hasExtra("id") && intent.hasExtra("username") &&
            intent.hasExtra("server") && intent.hasExtra("page")
        ){

            val idPlayer = intent.getStringExtra("id")
            val usernamePlayer = intent.getStringExtra("username")
            val serverPlayer = intent.getStringExtra("server")
            val pagePlayer = intent.getStringExtra("page")

            val txtEUsername = findViewById<EditText>(R.id.txt_edit_username)
            val txtEServer = findViewById<EditText>(R.id.txt_edit_server)
            val txtEPage = findViewById<EditText>(R.id.txt_edit_page)

            txtEUsername.setText(usernamePlayer)
            txtEServer.setText(serverPlayer)
            txtEPage.setText(pagePlayer)

        } else {
            Toast.makeText(this, "Tidak ada data!", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialogKonfirmasi(){
        val idPlayer       = intent.getStringExtra("id")
        val usernamePlayer = intent.getStringExtra("username")

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete " + usernamePlayer + " ?")
        alertDialog.setMessage("Apakah anda yakin menghapus " + usernamePlayer + " ?")

        alertDialog.setPositiveButton("Iya", DialogInterface.OnClickListener { dialog, which ->
            val dbAdmin3 = MyDBHelper(this)
            dbAdmin3.hapusPlayer(idPlayer)
            startActivity(Intent(this, MainActivity::class.java))
        })

        alertDialog.setNegativeButton("Tidak", DialogInterface.OnClickListener{ dialog, which -> })
        alertDialog.create().show()
    }
}