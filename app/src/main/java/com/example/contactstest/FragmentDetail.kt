package com.example.contactstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail.view.*

class FragmentDetail: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_detail,
        container, false)
        arguments?.getParcelable<ContactItem>(EXTRA_DETAIL_DATA)?.run {
            view.tv_name.text = this.name
            view.tv_email.text = this.email
            view.tv_address.text = this.address
            view.tv_phone.text = this.phone.toString()
        }
        return view
    }

    companion object{
        val EXTRA_DETAIL_DATA = "FragmentDetail"
        fun createFragmentDetail(dataItem: ContactItem): FragmentDetail{
            return FragmentDetail().apply {
                arguments = Bundle().apply {
                    this.putParcelable(EXTRA_DETAIL_DATA,
                    dataItem)
                }
            }
        }
    }
}