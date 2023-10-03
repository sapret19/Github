package com.hnf.cc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiConfig.apiService.getUsers("ha").enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                if (response.isSuccessful) {
                    val responseUser= response.body()
                    val dataUser = responseUser?.items
                    val userAdapter = MainAdapter(dataUser as List<ItemsItem>)
                        binding.rvUser.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            setHasFixedSize(true)
                            userAdapter.notifyDataSetChanged()
                            adapter = userAdapter
                        }
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}