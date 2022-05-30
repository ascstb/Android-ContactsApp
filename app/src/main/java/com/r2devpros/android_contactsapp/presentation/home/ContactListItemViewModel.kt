package com.r2devpros.android_contactsapp.presentation.home

import androidx.lifecycle.ViewModel
import com.r2devpros.android_contactsapp.model.Contact

class ContactListItemViewModel : ViewModel() {
    var contact: Contact? = null

    val name: String?
        get() = contact?.name

    val lastName: String?
        get() = contact?.lastName

    val fullName: String
        get() = "$name $lastName"
}