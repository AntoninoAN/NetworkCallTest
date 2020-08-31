package com.example.contactstest.view

import com.example.contactstest.model.ContactItem

interface OpenDetailFragment {
    fun openFragment(dataItem: ContactItem)
}