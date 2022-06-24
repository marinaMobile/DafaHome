package com.sportdafa.dafahome

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val url = "https://newsapi.org/"
const val key = "7533681e163444b0a9859db9edf87aba"


interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$key")

    fun getArticle(@Query("category") category: String, @Query("q") q: String) : Call<News>
}

object Service {
    val newsInst: NewsInterface

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInst = retrofit.create(NewsInterface::class.java)
    }
}