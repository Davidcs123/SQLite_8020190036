package com.example.latihan_03_sqlite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolderAdmin> {

    private Context context;
    private ArrayList player_id, player_username, player_server, player_page;

    PlayerAdapter(
            Context context,
            ArrayList player_id,
            ArrayList player_username,
            ArrayList player_server,
            ArrayList player_page
        ) {
        this.context            = context;
        this.player_id          = player_id;
        this.player_username    = player_username;
        this.player_server      = player_server;
        this.player_page        = player_page;
    }

    @NonNull
    @Override
    public ViewHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflaterAdmin = LayoutInflater.from(context);
        View viewadmin = inflaterAdmin.inflate(R.layout.row_admin, parent, false);
        return new ViewHolderAdmin(viewadmin);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdmin holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_id_player.setText(String.valueOf(player_id.get(position)));
        holder.txt_username_player.setText(String.valueOf(player_username.get(position)));
        holder.txt_server_player.setText(String.valueOf(player_server.get(position)));
        holder.txt_page_player.setText(String.valueOf(player_page.get(position)));
        holder.layoututama.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentAdmin2 = new Intent(context, UbahplayerActivity.class);
                intentAdmin2.putExtra("id", String.valueOf(player_id.get(position)));
                intentAdmin2.putExtra("username", String.valueOf(player_username.get(position)));
                intentAdmin2.putExtra("server", String.valueOf(player_server.get(position)));
                intentAdmin2.putExtra("page", String.valueOf(player_page.get(position)));

                context.startActivities(new Intent[]{intentAdmin2});
            }
        });
    }

    @Override
    public int getItemCount() {
        return player_id.size();
    }

    public class ViewHolderAdmin extends RecyclerView.ViewHolder {

        TextView txt_id_player, txt_username_player, txt_server_player, txt_page_player;
        LinearLayout layoututama;

        public ViewHolderAdmin(@NonNull View itemView){
            super(itemView);

            txt_id_player           = itemView.findViewById(R.id.txt_player_id);
            txt_username_player     = itemView.findViewById(R.id.txt_player_username);
            txt_server_player       = itemView.findViewById(R.id.txt_player_server);
            txt_page_player         = itemView.findViewById(R.id.txt_player_page);
            layoututama             = itemView.findViewById(R.id.layout_utama);
        }
    }
}
