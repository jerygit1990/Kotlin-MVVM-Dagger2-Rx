package com.jery.mvvm.example.uis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jery.mvvm.example.R
import com.jery.mvvm.example.uis.fragments.NewsFeedFragment
import com.jery.mvvm.example.uis.fragments.UserProfileFragment
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private var fragments = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        fragments.add(NewsFeedFragment.newInstance())
        fragments.add(UserProfileFragment.newInstance())

        var adapter = HomePagerAdapter(supportFragmentManager, fragments)
        view_pager.adapter = adapter

        home_tab.setupWithViewPager(view_pager)
        home_tab.getTabAt(0)!!.setIcon(R.drawable.ic_home_black_24dp)
        home_tab.getTabAt(1)!!.setIcon(R.drawable.ic_account_circle_black_24dp)
    }

    private class HomePagerAdapter(fragmentManager: FragmentManager, var fragments: List<Fragment>): FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0)
                return "Home"
            return "Account"
        }
    }
}
