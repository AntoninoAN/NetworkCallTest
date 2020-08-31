package com.example.contactstest.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

//Extension Functions
// fun <Target>.extensionFunctionName(anyParam: Any): Unit{}
fun Context.checkInternetConnection(): Boolean{
    val connectivityManager = this.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork
        connectivityManager.getNetworkCapabilities(activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }else{
        connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}