package com.r2devpros.android_contactsapp.repository.local.room

import android.content.Context
import androidx.room.Room
import com.r2devpros.android_contactsapp.model.Contact

class ContactsRepo(context: Context) {
    private val contactsDAO: ContactsDAO

    init {
        val contactsDatabase =
            Room.databaseBuilder(
                context,
                ContactsDatabase::class.java,
                "contacts-database"
            )
                .fallbackToDestructiveMigration()
                .build()

        contactsDAO = contactsDatabase.contactsDAO()
    }

    fun getContacts(): List<Contact> = contactsDAO.getContacts()

    fun addContact(contact: Contact) = contactsDAO.addContact(contact)

    fun deleteContactById(contactId: String) = contactsDAO.deleteContactById(contactId)
}