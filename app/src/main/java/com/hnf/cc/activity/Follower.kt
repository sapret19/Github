package com.hnf.cc.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnf.cc.R
import com.hnf.cc.activity.viewmodel.FollowViewModel
import com.hnf.cc.adapter.MainAdapter
import com.hnf.cc.databinding.FragmentFollowerBinding


class Follower : Fragment(R.layout.fragment_follower) {
    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_USERNAME = "app_name"
    }

    private lateinit var binding: FragmentFollowerBinding
    private lateinit var viewModel: FollowViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments
        username = arguments?.getString(DetailActivity.USERNAME).toString()

        binding = FragmentFollowerBinding.bind(view)

        adapter = MainAdapter(emptyList())

        binding.apply {
            listFollow.setHasFixedSize(true)
            listFollow.layoutManager = LinearLayoutManager(activity)
            listFollow.adapter = adapter
        }

        binding.progress.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowViewModel::class.java]
        viewModel.setFollow(username)

//        viewModel.getFollow().observe(viewLifecycleOwner) {
//            if (it != null) {
//                adapter.dataUser
//            }
//        }

        adapter.notifyDataSetChanged()

    }




}