package com.hnf.cc.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnf.cc.adapter.MainAdapter
import com.hnf.cc.api.ApiConfig
import com.hnf.cc.api.response.GithubResponse
import com.hnf.cc.api.response.ItemsItem
import com.hnf.cc.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = MainAdapter(emptyList())
        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    val query = searchView.text.toString()
                    getQuery(query)
                    false
                }
        }
        binding.progress.visibility = View.VISIBLE
        ApiConfig.apiService.getUsers("a").enqueue(object : Callback<GithubResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                binding.progress.visibility = View.GONE
                if (response.isSuccessful) {
                    val responseUser = response.body()
                    val dataUser = responseUser?.items
                    val userAdapter = MainAdapter(dataUser as List<ItemsItem>)
                    binding.rvUser.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = userAdapter


                    }
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }

        })

        }
    private fun getQuery(query: String) {
        binding.progress.visibility = View.VISIBLE
        ApiConfig.apiService.getUsers(query).enqueue(object : Callback<GithubResponse> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                binding.progress.visibility = View.GONE
                if (response.isSuccessful) {
                    val responseUser = response.body()
                    val dataUser = responseUser?.items
                    val userAdapter = MainAdapter(dataUser as List<ItemsItem>)
                    binding.rvUser.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        adapter = userAdapter


                    }
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    private fun setProgressBar(state: Boolean){
        binding.progress.visibility = if (state) View.VISIBLE else View.GONE
    }




}