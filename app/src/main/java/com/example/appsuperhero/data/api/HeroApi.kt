package com.example.appsuperhero.data.api

import com.example.appsuperhero.models.HeroModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {
    @GET("{id}")
    fun getHero(@Path("id") id: String): Call<HeroModel>
}