package com.example.contactstest.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contactstest.R
import com.example.contactstest.model.ContactItem
import com.example.contactstest.model.ContactResponse
import com.example.contactstest.model.NetworkContacts
import com.example.contactstest.model.RetrofitNetwork
import com.example.contactstest.presenter.PresenterDisplay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ACTION_NETWORK_BROADCAST = "NetworkBroadcastReceiver"
const val EXTRA_NETWORK_BROADCAST = "NetworkBroadcastReceiver"

class MainActivity : AppCompatActivity(), DisplayInterface {

//    val urlContacts: String = "https://api.androidhive.info/contacts/"
//    val urlBaseUrl = "https://api.androidhive.info/"
//    val urlEndpoint = "contacts"

    val presenterDisplay: PresenterDisplay by lazy{
        PresenterDisplay()
    }

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
        onBindPresenter()

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



//    RetrofitNetwork.initRetrofit().getMeContacts()
//    .enqueue(new Callback<ContactResponse>(){
//           onFailure(){}
//           onREsponse(){}
//    })



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
            .replace(
                R.id.fl_fragment_container,
            fragmentDetail)
            .addToBackStack(null)
            .setCustomAnimations(android.R.anim.slide_in_left,
            android.R.anim.fade_out)
            .commit()
    }

    override fun initNetworkCall() {
        presenterDisplay.initNetworkCall(this)
    }

    override fun displayData(dataSet: ContactResponse) {
        initFragment(dataSet)
    }

    override fun onBindPresenter() {
        presenterDisplay.onBind(this)
    }

    override fun errorMessage(errorMessage: String) {
        Toast.makeText(this@MainActivity, errorMessage,
            Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenterDisplay.unBind()
        super.onDestroy()
    }

//    fun registerCallbackConnection(){
//        val connectivityManager = this.getSystemService(
//            Context.CONNECTIVITY_SERVICE
//        ) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            connectivityManager.registerNetworkCallback(NetworkRequest.Builder()
//                .setNetworkSpecifier(object: NetworkSpecifier(){})
//                .addTransportType(TransportInfo())
//                ,ContactNetworkCallback)
//        }
//    }
//
//    object ContactNetworkCallback: ConnectivityManager.NetworkCallback() {
//        override fun onAvailable(network: Network) {
//            super.onAvailable(network)
//        }
//
//        override fun onCapabilitiesChanged(
//            network: Network,
//            networkCapabilities: NetworkCapabilities) {
//            super.onCapabilitiesChanged(network, networkCapabilities)
//
//        }
//    }
}





//public class Utility extends Fragment{
//    public static Boolean validateUserName(String userName){}
//    public static Date parseDate(String europeanDates){}
//    public Boolean checkVisiblity(){}
//}
