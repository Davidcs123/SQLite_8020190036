package com.example.latihan_03_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TambahPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_player)

        val txtusername    = findViewById<EditText>(R.id.txt_usdername)
        val txtserver      = findViewById<EditText>(R.id.txt_server)
        val txtpage        = findViewById<EditText>(R.id.txt_page)
        val btnsimpan      = findViewById<Button>(R.id.btn_simpan)

        btnsimpan.setOnClickListener {
        val dbAdminServerPlayer = MyDBHelper(this);
            dbAdminServerPlayer.tambahplayer(
                txtusername.text.toString().trim(),
                txtserver.text.toString().trim(),
                Integer.valueOf(txtpage.text.toString().trim())
            )
        }
    }
}