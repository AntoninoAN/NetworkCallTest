package com.example.contactstest.view

import com.example.contactstest.model.ContactResponse

interface DisplayInterface {
    fun initNetworkCall()
    fun displayData(dataSet: ContactResponse)
    fun onBindPresenter()
    fun errorMessage(errorMessage: String)
}