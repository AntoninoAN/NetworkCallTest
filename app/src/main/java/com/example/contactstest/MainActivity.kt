package com.example.contactstest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ACTION_NETWORK_BROADCAST = "NetworkBroadcastReceiver"
const val EXTRA_NETWORK_BROADCAST = "NetworkBroadcastReceiver"

class MainActivity : AppCompatActivity() {

    val urlContacts: String = "https://api.androidhive.info/contacts/"
    val urlBaseUrl = "https://api.androidhive.info/"
    val urlEndpoint = "contacts"

    //Lazy<FragmentDisplay> => UNINITIALIZED
    //invoke lambda
//    val displayFragment: FragmentDisplay by lazy {
//        FragmentDisplay()
//    }
    //df1 = Fr()
    //df1.passData()
    //df2 = Fr()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        registerNetworkBroadcast()
//
//        val contactExplicit = Intent(this,
//            ContactsIntentService::class.java)
//        contactExplicit.putExtra(EXTRA_IS_URL , urlContacts)
//        startService(contactExplicit)

        initNetworkCall()

//        val policy =
//            StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)

//        view.text =
//        Thread() {
//            //different Thread
//            //heavy operation
//            interface.passData(result)
//        }
    }
//    new Thread(new Runnable() {
//
//    });

    private fun getMeContacts(){
        val network = NetworkContacts(urlContacts)
        val contactsResponse =
            network.executeNetworkCall()
        val displayFragment = FragmentDisplay.newInstance(contactsResponse)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container,
                displayFragment)
            .commit()

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

//    RetrofitNetwork.initRetrofit().getMeContacts()
//    .enqueue(new Callback<ContactResponse>(){
//           onFailure(){}
//           onREsponse(){}
//    })

    fun initNetworkCall(){
        RetrofitNetwork.initRetrofit(this)
            .getMeContacts()
            .enqueue(object: Callback<ContactResponse>{
                override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Pay your internet connection",
                        Toast.LENGTH_SHORT).show();
                }

                override fun onResponse(
                    call: Call<ContactResponse>,
                    response: Response<ContactResponse>
                ) {
                    if(response.isSuccessful)
                        initFragment(response.body())
                }
            })
    }

    private fun initFragment(body: ContactResponse?) {
        body?.let {
            val fragmentDisplay =
                FragmentDisplay.newInstance(it)//<- NPE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_fragment_container, fragmentDisplay)
                //.addToBackStack(null)
                .commit()
        }
    }

    private fun registerNetworkBroadcast(){
        val contactIntentFilter = IntentFilter(ACTION_NETWORK_BROADCAST)
        val networkBR = NetworkBroadcastReceiver()
        registerReceiver(networkBR, contactIntentFilter)
    }

    inner class NetworkBroadcastReceiver: BroadcastReceiver() {
        /**
         * @param p0 Context
         * @param p1 Implicit intent (Broadcast)
         */
        override fun onReceive(p0: Context?, p1: Intent?) {
            // MainThread
            Log.d("NetworkBroadcastReceive","onReceive")
            if(p1?.action == ACTION_NETWORK_BROADCAST){
                p1.getParcelableExtra<ContactResponse>(EXTRA_NETWORK_BROADCAST)?.let {
                    val fragmentDisplay =
                    FragmentDisplay.newInstance(it)//<- NPE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_fragment_container, fragmentDisplay)
                        .commit()
                }
            }

        }
    }

    fun createFragmentDetail(dataItem: ContactItem){
        // todo create SecondFragment
        val fragmentDetail = FragmentDetail
            .createFragmentDetail(dataItem)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container,
            fragmentDetail)
            .addToBackStack(null)
            .setCustomAnimations(android.R.anim.slide_in_left,
            android.R.anim.fade_out)
            .commit()
    }

}

//Extension Functions
// fun <Target>.extensionFunctionName(anyParam: Any): Unit{}
fun Context.checkInternetConnection(): Boolean{
    val connectivityManager = this.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val activeNetwork =  connectivityManager.activeNetworkInfo
    return activeNetwork?.isConnected?: false
}

//public class Utility extends Fragment{
//    public static Boolean validateUserName(String userName){}
//    public static Date parseDate(String europeanDates){}
//    public Boolean checkVisiblity(){}
//}
