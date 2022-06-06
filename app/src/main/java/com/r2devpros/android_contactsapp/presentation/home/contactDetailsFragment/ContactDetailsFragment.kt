package com.r2devpros.android_contactsapp.presentation.home.contactDetailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.r2devpros.android_contactsapp.databinding.ContactDetailsFragmentLayoutBinding
import com.r2devpros.android_contactsapp.model.Contact
import timber.log.Timber

class ContactDetailsFragment : Fragment() {
    private lateinit var layout: ContactDetailsFragmentLayoutBinding
    private lateinit var vm: ContactDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("ContactDetailsFragment_TAG: onCreateView: ")
        ContactDetailsFragmentLayoutBinding.inflate(layoutInflater, container, false).run {
            Timber.d("ContactDetailsFragment_TAG: onCreateView: onRun")
            vm = ContactDetailsViewModel()
            layout = this
            layout.lifecycleOwner = this@ContactDetailsFragment
            layout.viewModel = vm
            layout.executePendingBindings()
            return this.root
        }
    }

    fun displayContactInfo(contact: Contact) {
        Timber.d("ContactDetailsFragment_TAG: displayContactInfo: $contact")
        vm.contact = contact
    }
}