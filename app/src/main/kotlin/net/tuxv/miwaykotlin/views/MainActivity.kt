package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import kotlinx.android.synthetic.activity_main.pager
import kotlinx.android.synthetic.activity_main.pagerTabStrip
import net.tuxv.miwaykotlin
import net.tuxv.miwaykotlin.R

public class MainActivity : FragmentActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.setAdapter(PagerAdapter(getSupportFragmentManager()))
        pagerTabStrip.setTabIndicatorColor(R.color.accent)
    }

    class PagerAdapter : FragmentPagerAdapter {

        val titles = array("Favourites", "Routes")

        public constructor(fm : FragmentManager) : super(fm)

        override fun getCount() : Int = titles.size()
        override fun getPageTitle(position: Int): CharSequence? = titles.get(position)

        override fun getItem(position: Int): Fragment? = when (position) {
            0 -> FavouritesFragment()
            1 -> miwaykotlin.RoutesFragment()
            else -> FavouritesFragment()
        }
    }
}