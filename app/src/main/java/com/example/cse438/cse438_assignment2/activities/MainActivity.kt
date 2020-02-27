package com.example.cse438.cse438_assignment2.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.adapter.SongGridAdapter
import com.example.cse438.cse438_assignment2.data.Song
import com.example.cse438.cse438_assignment2.fragments.HomePage
import com.example.cse438.cse438_assignment2.fragments.PlaylistFragment
import com.example.cse438.cse438_assignment2.fragments.PopularsongFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
        tabs_main.setupWithViewPager(viewpager_main)
        viewpager_main.setCurrentItem(intent!!.getIntExtra("tab",0),true)
        }

    class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount() : Int{
            return 3
        }
        override fun getItem(position:Int): Fragment {

            return when (position){
                0 -> {
                    HomePage()
                }
                1 ->{
                    PlaylistFragment()
                }
                else -> {
                    PopularsongFragment()
                }
            }
        }

        override fun getPageTitle(position: Int) : CharSequence{
            return when (position){
                0 -> "Homepage"
                1 -> "Playlist"
                else -> "Popular Song"
            }
        }

    }
}
