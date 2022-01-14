package com.kmv.android.doggies.retrofit

object Common {
    private val BASE_URL = "http://shibe.online/api/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
