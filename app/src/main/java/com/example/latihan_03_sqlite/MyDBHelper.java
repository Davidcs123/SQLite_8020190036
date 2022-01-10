package com.example.latihan_03_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Server.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "player";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "player_username";
    private static final String COLUMN_SERVER = "player_server";
    private static final String COLUMN_PAGE = "player_page";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryPlayer = "CREATE TABLE " + TABLE_NAME +
                " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_SERVER + " TEXT, " +
                COLUMN_PAGE + " INTEGER" +
                ");";

        db.execSQL(queryPlayer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void tambahplayer(String username, String server, int page) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_SERVER, server);
        cv.put(COLUMN_PAGE, page);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data ditambahkan!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor bacaSemuaData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor dataAdminServerPlayer = null;
        if (db != null) {
            dataAdminServerPlayer = db.rawQuery(query, null);
        }

        return dataAdminServerPlayer;
    }

    void ubahPlayer(String baris_id, String username, String server, String page){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataAdmin = new ContentValues();
        dataAdmin.put(COLUMN_USERNAME, username);
        dataAdmin.put(COLUMN_SERVER, server);
        dataAdmin.put(COLUMN_PAGE, page);

        long hasil = db.update(TABLE_NAME, dataAdmin, "_id=?", new String[]{baris_id});

        if (hasil == -1){
            Toast.makeText(context, "Ada Gangguan Berhasil di ubah!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil di ubah!", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusPlayer(String row_id){
        SQLiteDatabase dbAdmin = this.getReadableDatabase();
        long result = dbAdmin.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "Gagal Delete!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Delete Berhasil", Toast.LENGTH_SHORT).show();
        }
    }
}
