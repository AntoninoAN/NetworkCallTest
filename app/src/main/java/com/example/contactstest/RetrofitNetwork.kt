package com.example.contactstest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//data classes
//dependencies
//interface
//retrofit builder
interface RetrofitNetwork {
    @GET("contacts")
    fun getMeContacts(): Call<ContactResponse>

    companion object{
        fun initRetrofit(): RetrofitNetwork{
            return Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitNetwork::class.java)
        }
    }
    //@GET("contacts")
    //Call<ContactResponse> getMeContacts();
}