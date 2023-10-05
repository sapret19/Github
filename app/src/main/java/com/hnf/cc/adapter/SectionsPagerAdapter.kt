package com.hnf.cc.adapter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hnf.cc.R
import com.hnf.cc.activity.Follower

class SectionsPagerAdapter(
    private val context: Context,
    manager: FragmentManager,
    data: Bundle
) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var bundle: Bundle

    init {
        bundle = data
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var pagerFragment: Fragment? = null
        pagerFragment = if (position == 0){
            Follower()
        } else {
            Follower()
        }
        pagerFragment?.arguments = this.bundle
        return pagerFragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context.resources.getString(R.string.tab_1)
            1 -> return context.resources.getString(R.string.tab_2)
        }
        return null
    }
}