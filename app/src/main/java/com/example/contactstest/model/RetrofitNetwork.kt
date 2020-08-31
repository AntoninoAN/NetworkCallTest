package com.example.contactstest.model

import android.content.Context
import com.example.contactstest.utility.checkInternetConnection
import okhttp3.Cache
import okhttp3.OkHttpClient
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
        fun initRetrofit(context: Context): RetrofitNetwork {
            return Retrofit.Builder()
                .client(
                    createCacheClient(
                        context
                    )
                )
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitNetwork::class.java)
        }

        private fun createCacheClient(context: Context): OkHttpClient {
            // cache size
            // 1MB
            val cacheSize = (1 * 1024 * 1024).toLong()
            val cache = Cache(context.cacheDir, cacheSize)
            val cacheBuilder = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor{
                    var request = it.request()
                    request = if(context.checkInternetConnection()){
                        //true -> is connected
                        request.newBuilder().build()
                    }else{
                        request.newBuilder().addHeader("Cache-Control",
                            "public, only-if-cached, max-stale=" +10).build()
                    }
                    it.proceed(request)
                }
            return cacheBuilder.build()
        }
    }
    //@GET("contacts")
    //Call<ContactResponse> getMeContacts();
}