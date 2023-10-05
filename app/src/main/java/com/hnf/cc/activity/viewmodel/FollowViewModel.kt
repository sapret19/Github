package com.hnf.cc.activity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hnf.cc.api.ApiConfig
import com.hnf.cc.api.response.FollowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {
    private val followers = MutableLiveData<ArrayList<FollowResponse>>()

    fun setFollow(user: String){
        val retrofit = ApiConfig.apiService
            .getFollower(user)
            .enqueue(object : Callback<ArrayList<FollowResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<FollowResponse>>,
                    response: Response<ArrayList<FollowResponse>>
                ) {
                    if (response.isSuccessful){
                        followers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<FollowResponse>>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getFollow():LiveData<ArrayList<FollowResponse>>{
        return followers
    }
}