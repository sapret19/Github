package com.hnf.cc.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hnf.cc.activity.Follower

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment = Follower()
        fragment.arguments = Bundle().apply {
            putInt(Follower.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }

}