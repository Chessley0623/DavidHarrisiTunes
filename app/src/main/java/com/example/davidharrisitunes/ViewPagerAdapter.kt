package com.example.davidharrisitunes

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {iTunesFragment.instanceMade(iTunesFragment.Rock)}
            1 -> {iTunesFragment.instanceMade(iTunesFragment.Classic)}
            2 -> {iTunesFragment.instanceMade(iTunesFragment.Pop)}
            else -> {throw Resources.NotFoundException("Tab does not exist")}
        }
    }

    override fun getItemCount() = 3
}