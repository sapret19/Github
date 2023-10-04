package com.hnf.cc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hnf.cc.R
import com.hnf.cc.adapter.SectionsPagerAdapter
import com.hnf.cc.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val USERNAME = "username"
        const val AVATAR = "avatar"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(USERNAME)
        val avatar = intent.getStringExtra(AVATAR)

        binding.namaUser.text = username
        Glide.with(this)
            .load(avatar)
            .centerCrop()
            .into(binding.avatarUser)

        sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.profileTab, binding.viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
}