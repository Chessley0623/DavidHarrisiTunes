package com.example.davidharrisitunes

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewpager2)

        viewPager.adapter = ViewPagerAdapter(this)

        //set tab names
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->

            //set tab icons
            tab.icon = when (index) {
                0 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_electric_guitar_svgrepo_com)
                }
                1 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_piano_svgrepo_com)
                }
                2 -> {
                    AppCompatResources.getDrawable(this, R.drawable.ic_microphone_svgrepo_com)
                }
                else -> {
                    throw Resources.NotFoundException("Tab does not exist")
                }
            }

            tab.text = when (index) {
                0 -> {
                    "Rock"
                }
                1 -> {
                    "Classic"
                }
                2 -> {
                    "Pop"
                }
                else -> {
                    throw Resources.NotFoundException("Tab does not exist")
                }
            }
        }.attach()
        supportActionBar?.hide()
    }
}