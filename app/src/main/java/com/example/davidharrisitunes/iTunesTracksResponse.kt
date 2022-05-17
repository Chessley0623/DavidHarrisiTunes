package com.example.davidharrisitunes

data class ITunesTracksResponse(
    val results : List<ITunesSong>
)

data class ITunesSong(
    val artistName : String,
    val collectionName : String,
    val trackName : String,
    val artworkUrl60 : String,
    val trackPrice : String,
    val previewUrl : String
)