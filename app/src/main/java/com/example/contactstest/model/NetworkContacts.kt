package com.example.contactstest.model

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.NullPointerException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Query and Return ContactResponse
 */
class NetworkContacts(val url: String) {

    fun executeNetworkCall(): ContactResponse {
        var contactURL = URL(url)
        var httpURLConnection: HttpURLConnection =
            contactURL.openConnection() as HttpURLConnection
        //Configure the HTTPUrlConnection
        httpURLConnection.connectTimeout = 10000
        httpURLConnection.readTimeout = 15000
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.doInput = true
        //Execute the Connection
        httpURLConnection.connect()
        //Receive the Data
        val inputStream = httpURLConnection.inputStream
        //Receive the Server Response
        val responseCode = httpURLConnection.responseCode
        val stringResponse = parseIStoString(inputStream)
        val contactResponse: ContactResponse = parsePoko(stringResponse)

        return contactResponse
    }

    private fun parsePoko(stringResponse: String?): ContactResponse {
        if(stringResponse == null)
            throw NullPointerException()

        val jsonResponse: JSONObject = JSONObject(stringResponse)
        val jsonArray = jsonResponse.getJSONArray("contacts")
        //0 to n -> .. [0],[1],
        //0 to n-1 -> until
        var phoneItem: PhoneItem
        var contactItem: ContactItem
        val listOfContactItems = mutableListOf<ContactItem>()//= new ArrayList<ContactItem>()

        for(index in 0 until jsonArray.length()){
            val jsonItem = jsonArray.get(index) as JSONObject
            val jsonItemPhone = jsonItem.getJSONObject("phone")
            phoneItem = PhoneItem(
                jsonItemPhone.getString("mobile"),
                jsonItemPhone.getString("home"),
                jsonItemPhone.getString("office")
            )

            contactItem = ContactItem(
                phone = phoneItem,
                id = jsonItem.getString("id"),
                name = jsonItem.getString("name"),
                gender = jsonItem.getString("gender"),
                email = jsonItem.getString("email"),
                address = jsonItem.getString("address")
            )
            listOfContactItems.add(contactItem)
        }
        return ContactResponse(listOfContactItems)
    }

    private fun parseIStoString(inputStream: InputStream): String?{
        val builder:java.lang.StringBuilder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line:String? = reader.readLine()
        while (line != null) {
            builder.append("$line\n")
            line = reader.readLine()// line = null
        }
        if (builder.length == 0) {
            return null
        }
        return builder.toString()
    }
}
