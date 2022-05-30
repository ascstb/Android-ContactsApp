package com.r2devpros.android_contactsapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r2devpros.android_contactsapp.model.Contact
import com.r2devpros.android_contactsapp.repository.RepositoryManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val repositoryManager: RepositoryManager
) : ViewModel() {
    val availableContacts = MutableLiveData<List<Contact>>()

    fun getContacts() = CoroutineScope(Dispatchers.IO).launch {
        Timber.d("HomeViewModel_TAG: getContacts: ")
        val result = repositoryManager.getContacts()
        Timber.d("HomeViewModel_TAG: getContacts: result: ${result.size}")
        availableContacts.postValue(result)
    }
}