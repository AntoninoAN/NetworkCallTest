package com.example.contactstest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class ContactsAdapter(val dataSet: ContactResponse): RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(){

    class ContactsViewHolder(contactsItem: View):
        RecyclerView.ViewHolder(contactsItem){
        val tvItemName: TextView =
            contactsItem.tv_item_name
        val tvItemEmail: TextView =
            contactsItem.tv_item_email
        /*
        public ContactsViewHolder(View contactsItem){
             val tvItemName: TextView =
            contactsItem.tv_item_name
             val tvItemEmail: TextView =
            contactsItem.tv_item_email
        }
         */
    }

    /**
     * Creates the specific ViewHolder
     * defined in the Parent Type.
     * @return the ViewHolder defines in Adapter.
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int)
            : ContactsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        val contactsViewHolder = ContactsViewHolder(view)
        return contactsViewHolder
    }

    /**
     * Hold the reference of N items
     * passed to the Adapter
     */
    override fun getItemCount(): Int {
        return dataSet.contacts.size
    }

    /**
     * Connects the data field with the
     * specific View.
     */
    override fun onBindViewHolder(holder: ContactsViewHolder,
                                  position: Int) {
        holder.tvItemName.text = dataSet.contacts[position].name
        holder.tvItemEmail.text = dataSet.contacts[position].email
    }

}