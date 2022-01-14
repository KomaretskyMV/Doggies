package com.kmv.android.doggies.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("shibes")
    fun getShibesList(@Query("count") count: Int): Call<List<String>>
}