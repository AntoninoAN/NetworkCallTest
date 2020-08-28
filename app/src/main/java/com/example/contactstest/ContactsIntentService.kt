package com.example.contactstest

import android.app.IntentService
import android.content.Intent
import android.util.Log

const val EXTRA_IS_URL = "ContactsIntentService"

class ContactsIntentService : IntentService("Contacts") {
    /**
     * @param p0 Explicit intent used for this IS
     */
    override fun onHandleIntent(p0: Intent?) {
        Log.d("ContactsIntentSErvice", "onHandleIntent")
        p0?.getStringExtra(EXTRA_IS_URL)?.let {
            val contactResponse: ContactResponse =
                NetworkContacts(it).run {
                    executeNetworkCall()
                }
            //todo send the Broadcast...
            Intent(ACTION_NETWORK_BROADCAST).also {myIntent->
                myIntent.putExtra(EXTRA_NETWORK_BROADCAST, contactResponse)
                baseContext.sendBroadcast(myIntent)
            }
        }

//        if(p0 != null) {
//            var url = p0.getStringExtra(EXTRA_IS_URL)
//            var netw = NetworkContacts(url)
//            netw.executeNetworkCall()
//        }
    }

}