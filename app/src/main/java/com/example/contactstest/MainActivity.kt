package com.example.contactstest

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val urlContacts: String = "https://api.androidhive.info/contacts/"
    val urlBaseUrl = "https://api.androidhive.info/"
    val urlEndpoint = "contacts"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

//        view.text =
//        Thread() {
//            //different Thread
//            //heavy operation
//            interface.passData(result)
//        }
        getMeContacts()
    }
//    new Thread(new Runnable() {
//
//    });

    private fun getMeContacts(){
        val network = NetworkContacts(urlContacts)
        val contactsResponse =
            network.executeNetworkCall()
        var counter = 0

//        contactsResponse.contacts.count({
//            counter++
//            if(counter < 5)
//        }).forEach {
//            Log.d("MainActivity", it.name)
//        }

        Toast.makeText(this,
            contactsResponse.toString(),
            Toast.LENGTH_LONG).show()
    }
}