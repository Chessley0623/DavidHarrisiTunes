package com.example.davidharrisitunes

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface iTunesAPI {

    companion object{
        private const val baseURL = "https://itunes.apple.com/"
        var instance: Retrofit? = null

        fun retrofitCreated(): Retrofit{
            if(instance == null){
                instance = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return  instance!!
        }
    }
    @GET("search?term=rock&amp;media=music&entity=song&limit=50")
    fun rockSongsTab(
    ): Call<ITunesTracksResponse>

    @GET("search?term=classic&amp;media=music&entity=song&limit=50")
    fun classicSongsTab(
    ): Call<ITunesTracksResponse>

    @GET("search?term=pop&amp;media=music&entity=song&limit=50")
    fun popSongsTab(
    ): Call<ITunesTracksResponse>
}