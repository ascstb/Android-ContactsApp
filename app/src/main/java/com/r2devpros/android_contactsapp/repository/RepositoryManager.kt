package com.r2devpros.android_contactsapp.repository

import com.r2devpros.android_contactsapp.model.Contact
import com.r2devpros.android_contactsapp.repository.local.room.ContactsRepo

class RepositoryManager(
    private val contactsRepo: ContactsRepo
) {
    fun getContacts(): List<Contact> {
        return contactsRepo.getContacts()
    }

    fun addContact(contact: Contact) {
        return contactsRepo.addContact(contact)
    }

    fun deleteContactById(contactId: String) {
        return contactsRepo.deleteContactById(contactId)
    }
}