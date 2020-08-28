package com.example.contactstest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
PARCELABLE AND SERIALIZABLE
Process to decompose complex data. => Marshalling
ContactResponse => decompose
Reconstruction == ContactResponse

PARCELABLE  ANDROID (android.os) => Parcel data container for IPC
Inter Process Communication
SERIALIZABLE  JAVA (javax.seria..?) => Java Reflection
 */

@Parcelize
data class ContactResponse(val contacts: List<ContactItem>): Parcelable

@Parcelize
data class ContactItem(
    val id: String,
    val name: String,
    val email: String,
    val address: String,
    val gender: String,
    val phone: PhoneItem): Parcelable

/*
public class ContactItem{
    final String id;
    final String name;
    final String email;
    final String address;
    final String gender;
    final String phone;

    public ContactItem(String id, String name, String email, String addre){
        this.id = id;
        this.name = name....
    }

    @Override
    public String toString(){}
    @Override
    public int hashCode(){}
    @Override
    public ContactItem copy(){}

}
 */
@Parcelize
data class PhoneItem(
    val mobile: String,
    val home: String,
    val office: String): Parcelable
//data class TmoResponse(page: TmoItem)
//data class TmoItem(val cards: List<CardItem>)
//data class CardItem(val value: String,
//                    val title: CardTitle,
//                    val description: CardDescription,
//                    val image: ImageDescription)
