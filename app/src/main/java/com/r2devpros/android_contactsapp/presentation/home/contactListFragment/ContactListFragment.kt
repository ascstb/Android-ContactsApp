package com.r2devpros.android_contactsapp.presentation.home.contactListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r2devpros.android_contactsapp.databinding.ContactListFragmentLayoutBinding
import com.r2devpros.android_contactsapp.model.Contact
import com.r2devpros.android_contactsapp.presentation.home.ContactListItemViewModel
import com.r2devpros.android_contactsapp.presentation.home.RVContactsAdapter
import timber.log.Timber

class ContactListFragment(
    private val listener: ContactListListener
) : Fragment() {
    private lateinit var rvContactsAdapter: RVContactsAdapter
    private lateinit var layout: ContactListFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("ContactListFragment_TAG: onCreateView: ")
        ContactListFragmentLayoutBinding.inflate(layoutInflater, container, false).run {
            layout = this
            initRecyclerView()
            return this.root
        }
    }

    private fun initRecyclerView() {
        Timber.d("ContactListFragment_TAG: initRecyclerView: ")
        rvContactsAdapter = RVContactsAdapter { contact ->
            onContactClicked(contact)
        }
        layout.rvContacts.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = rvContactsAdapter
        }
        rvContactsAdapter.itemList = emptyList()
    }

    fun setContacts(contacts: List<Contact>) {
        Timber.d("ContactListFragment_TAG: setContacts: ${contacts.size}")
        val tempList = contacts.map { contactItem ->
            ContactListItemViewModel().apply {
                contact = contactItem
            }
        }

        rvContactsAdapter.itemList = tempList
    }

    private fun onContactClicked(contact: Contact) {
        Timber.d("ContactListFragment_TAG: onContactClicked: ${contact.name} ${contact.lastName}")
        listener.onContactClicked(contact)
    }
}