package com.example.contactstest

data class ContactResponse(val contacts: List<ContactItem>)

data class ContactItem(
    val id: String,
    val name: String,
    val email: String,
    val address: String,
    val gender: String,
    val phone: PhoneItem)

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

data class PhoneItem(
    val mobile: String,
    val home: String,
    val office: String
)
//data class TmoResponse(page: TmoItem)
//data class TmoItem(val cards: List<CardItem>)
//data class CardItem(val value: String,
//                    val title: CardTitle,
//                    val description: CardDescription,
//                    val image: ImageDescription)
