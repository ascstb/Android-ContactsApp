package com.r2devpros.android_contactsapp.presentation.addContact

import androidx.lifecycle.ViewModel
import com.r2devpros.android_contactsapp.model.Contact
import com.r2devpros.android_contactsapp.repository.RepositoryManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AddContactViewModel(
    private val repositoryManager: RepositoryManager
) : ViewModel() {
    var name: String = ""
    var lastName: String = ""
    var genre: String = ""
    var age: String = ""

    val contact: Contact
        get() = Contact(
            name = name,
            lastName = lastName,
            genre = genre,
            age = age.toIntOrNull() ?: 0
        )

    fun addContactClicked() = CoroutineScope(Dispatchers.IO).launch {
        Timber.d("AddContactViewModel_TAG: addContact: $contact")
        repositoryManager.addContact(contact)
    }
}