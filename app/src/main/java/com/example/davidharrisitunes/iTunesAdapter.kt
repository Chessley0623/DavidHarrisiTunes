package com.example.davidharrisitunes

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class iTunesAdapter(private val songs: List<ITunesSong>) : RecyclerView.Adapter<iTunesAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(iTunesSong: ITunesSong) {

            val artistName: TextView = itemView.findViewById(R.id.name_of_artist)
            val songName: TextView = itemView.findViewById(R.id.name_of_song)

            val coverArt: ImageView = itemView.findViewById(R.id.cover_art)
            val albumName: TextView = itemView.findViewById(R.id.album)

            val price: TextView = itemView.findViewById(R.id.price)

            //button for youtube music
            itemView.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(Uri.parse(iTunesSong.previewUrl), "audio/*")
                itemView.context.startActivity(intent)
            }

            artistName.text = iTunesSong.artistName
            songName.text = iTunesSong.trackName
            albumName.text = iTunesSong.collectionName

            Picasso.get()
                .load(iTunesSong.artworkUrl60)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit()
                .into(coverArt)

            price.text = "$" + iTunesSong.trackPrice.toString()
            price.text = price.text.replace("^[$][-].*$".toRegex(), "Limited Time - Free")
        }
    }
    //holder for the list UI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val listOfSongs = LayoutInflater.from(parent.context).inflate(R.layout.itunes_card, parent, false)

        return CardViewHolder(listOfSongs)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(songs[position])
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}