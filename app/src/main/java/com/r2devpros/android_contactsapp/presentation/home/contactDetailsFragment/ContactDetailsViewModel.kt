package com.r2devpros.android_contactsapp.presentation.home.contactDetailsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.r2devpros.android_contactsapp.model.Contact

class ContactDetailsViewModel : ViewModel() {
    var contact: Contact? = null
        set(value) {
            field = value

            if (value == null) {
                labelsVisible.postValue(false)
            } else {
                labelsVisible.postValue(true)
            }
            photoUrl.postValue(value?.photo)
            name.postValue(value?.name)
            lastName.postValue(value?.lastName)
            genre.postValue(value?.genre)
            age.postValue(value?.age?.toString())
        }

    val photoUrl = MutableLiveData("")
    val name = MutableLiveData("")
    val lastName = MutableLiveData("")
    val genre = MutableLiveData("")
    val age = MutableLiveData("")

    val labelsVisible = MutableLiveData(false)
}