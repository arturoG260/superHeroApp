package com.example.appsuperhero.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.appsuperhero.data.api.HeroApi
import com.example.appsuperhero.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroViewModel(controller: Application) : AndroidViewModel(controller) {
    private var heroeList: ArrayList<HeroModel> = arrayListOf()
    var heroeMutableList = MutableLiveData<ArrayList<HeroModel>>()
    var progress = MutableLiveData<Boolean>()
    var id = 0
    fun getHeros() {
        progress.postValue(true)
        id++
        if (id >= 731) {
            progress.postValue(false)
            return
        }
        RetrofitClient.createApi(HeroApi::class.java).getHero("$id")
            .enqueue(object : Callback<HeroModel> {
                override fun onResponse(
                    call: Call<HeroModel>,
                    response: Response<HeroModel>
                ) {
                    heroeList.add(
                        response.body()!!
                    )
                    heroeMutableList.postValue(heroeList)
                    getHeros()
                    //Log.e("Listo", response.body()!!.name)
                }

                override fun onFailure(call: Call<HeroModel>, t: Throwable) {
                    //Log.e("Error", t.message!!)
                    getHeros()
                }
            })
    }
}