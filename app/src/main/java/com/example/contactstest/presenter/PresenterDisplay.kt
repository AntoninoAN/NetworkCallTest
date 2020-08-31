package com.example.contactstest.presenter

import android.content.Context
import android.widget.Toast
import com.example.contactstest.R
import com.example.contactstest.model.ContactResponse
import com.example.contactstest.model.NetworkContacts
import com.example.contactstest.model.RetrofitNetwork
import com.example.contactstest.view.DisplayInterface
import com.example.contactstest.view.FragmentDisplay
import com.example.contactstest.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterDisplay {

    private var view: DisplayInterface? = null

    fun onBind(view: DisplayInterface){
        this.view = view
    }

    fun unBind(){
        view = null
    }

    fun initNetworkCall(context: Context){
        RetrofitNetwork.initRetrofit(context)
            .getMeContacts()
            .enqueue(object: Callback<ContactResponse> {
                override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                    view?.errorMessage("Internet")
                }

                override fun onResponse(
                    call: Call<ContactResponse>,
                    response: Response<ContactResponse>
                ) {
                    if(response.isSuccessful)
                        response.body()?.let {
                            view?.displayData(it)
                        }
                }
            })
    }

    private fun getMeContacts(context: Context){
        val network = NetworkContacts(context.getString(R.string.urlContacts))
        val contactsResponse =
            network.executeNetworkCall()

        view?.displayData(contactsResponse)

//        val displayFragment = FragmentDisplay.newInstance(contactsResponse)
//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.fl_fragment_container,
//                displayFragment)
//            .commit()

//        var counter = 0
//        contactsResponse.contacts.count({
//            counter++
//            if(counter < 5)
//        }).forEach {
//            Log.d("MainActivity", it.name)
//        }
//        Toast.makeText(this,
//            contactsResponse.toString(),
//            Toast.LENGTH_LONG).show()
    }
}