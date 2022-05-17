package com.example.davidharrisitunes

import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class iTunesFragment : Fragment() {

    private var musicGenre: Int = Rock

    lateinit var iTunesAdapter: iTunesAdapter
    lateinit var refresh: SwipeRefreshLayout
    lateinit var trackList: RecyclerView

    //make the card for the appropriate genre
    fun tracks(inflater: LayoutInflater, view: View) {
        musicGenre = requireArguments().getInt(KEY)

        if (musicGenre == Rock) {
            retrofitStarter(
                inflater, iTunesAPI.retrofitCreated().create(iTunesAPI::class.java).rockSongsTab()
            )
            view.setBackgroundResource(R.drawable.rock_background)
        } else if (musicGenre == Classic) {
            retrofitStarter(
                inflater, iTunesAPI.retrofitCreated().create(iTunesAPI::class.java).classicSongsTab()
            )
            view.setBackgroundResource(R.drawable.classic_background)
        } else if (musicGenre == Pop) {
            retrofitStarter(
                inflater, iTunesAPI.retrofitCreated().create(iTunesAPI::class.java).popSongsTab()
            )
            view.setBackgroundResource(R.drawable.pop_background)
        }
    }

    companion object {

        const val KEY = "MUSIC_TYPE"

        const val Rock = 0
        const val Classic = 1
        const val Pop = 2

        fun instanceMade(musicGenre: Int): iTunesFragment {
            val iTunesFragment = iTunesFragment()
            val bundle = Bundle()

            bundle.putInt(KEY, musicGenre)
            iTunesFragment.arguments = bundle

            return iTunesFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.itunes_layout, container, false)

        refresh = view.findViewById(R.id.refresh)
        trackList = view.findViewById(R.id.songs)

        tracks(inflater, view)

        refresh.setOnRefreshListener {

            refresh.isRefreshing = true

            tracks(inflater, view)

        }

        return view
    }

    private fun retrofitStarter(inflater: LayoutInflater, call: Call<ITunesTracksResponse>) {
        call.enqueue(object : Callback<ITunesTracksResponse> {
            override fun onResponse(
                call: Call<ITunesTracksResponse>,
                response: Response<ITunesTracksResponse>
            ) {
                if (response.isSuccessful) {
                    iTunesAdapter = iTunesAdapter(response.body()!!.results)
                    trackList.adapter = iTunesAdapter
                    refresh.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<ITunesTracksResponse>, t: Throwable) {
                Toast.makeText(inflater.context, t.message, Toast.LENGTH_LONG).show()
                refresh.isRefreshing = false
            }
        })
    }
}