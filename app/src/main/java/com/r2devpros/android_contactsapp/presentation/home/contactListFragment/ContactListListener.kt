package com.r2devpros.android_contactsapp.presentation.home.contactListFragment

import com.r2devpros.android_contactsapp.model.Contact

interface ContactListListener {
    fun onContactClicked(contact: Contact)
}