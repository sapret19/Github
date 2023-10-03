package com.hnf.cc.api

import com.hnf.cc.api.response.DetailResponse
import com.hnf.cc.api.response.FollowResponse
import com.hnf.cc.api.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
//    @Headers("Authorization: Bearer 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")
    fun getUsers(
        @Query("q") query: String
//        @Header("Authorization") authToken: String
    ) : Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollower(
        @Path("username") username: String
    ): Call<ArrayList<FollowResponse>>

    @GET("users/{username}/followers")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<FollowResponse>>
}