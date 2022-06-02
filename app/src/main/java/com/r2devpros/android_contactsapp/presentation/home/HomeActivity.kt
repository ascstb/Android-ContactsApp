package com.r2devpros.android_contactsapp.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.r2devpros.android_contactsapp.R
import com.r2devpros.android_contactsapp.databinding.HomeActivityLayoutBinding
import com.r2devpros.android_contactsapp.model.Contact
import com.r2devpros.android_contactsapp.presentation.addContact.AddContactActivity
import com.r2devpros.android_contactsapp.presentation.home.contactListFragment.ContactListFragment
import com.r2devpros.android_contactsapp.presentation.home.contactListFragment.ContactListListener
import com.r2devpros.android_contactsapp.repository.RepositoryManager
import com.r2devpros.android_contactsapp.repository.local.room.ContactsRepo
import timber.log.Timber

class HomeActivity : AppCompatActivity() {
    private lateinit var layout: HomeActivityLayoutBinding
    private lateinit var viewModel: HomeViewModel
    private var contactListFragment: ContactListFragment? = null

    //region Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("HomeActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
        layoutBinding()
        bindViews()
        initFragments()

        bindObservers()
    }

    override fun onResume() {
        Timber.d("HomeActivity_TAG: onResume: ")
        super.onResume()
        viewModel.getContacts()
    }
    //endregion

    //region Binding
    private fun layoutBinding() {
        Timber.d("HomeActivity_TAG: layoutBinding: ")
        viewModel = HomeViewModel(
            repositoryManager = RepositoryManager(
                ContactsRepo(this)
            )
        )

        layout = DataBindingUtil.setContentView(this, R.layout.home_activity_layout)
        layout.lifecycleOwner = this
    }

    private fun bindViews() {
        layout.btnGoToAdd.setOnClickListener {
            addContactClicked()
        }
    }

    private fun initFragments() {
        Timber.d("HomeActivity_TAG: initFragments: ")

        contactListFragment = ContactListFragment(object : ContactListListener {
            override fun onContactClicked(contact: Contact) {
                Timber.d("HomeActivity_TAG: onContactClicked: $contact")
            }
        })

        contactListFragment?.let {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.flContactList, it)
                .commit()
        }
    }

    private fun bindObservers() {
        Timber.d("HomeActivity_TAG: bindObservers: ")
        viewModel.availableContacts.observe(this) { contacts ->
            contactListFragment?.setContacts(contacts)
        }
    }
    //endregion

    //region Functions
    private fun addContactClicked() {
        Timber.d("HomeActivity_TAG: addContactClicked: ")
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }
    //endregion
}