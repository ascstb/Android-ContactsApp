package com.r2devpros.android_contactsapp.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r2devpros.android_contactsapp.R
import com.r2devpros.android_contactsapp.databinding.HomeActivityLayoutBinding
import com.r2devpros.android_contactsapp.presentation.addContact.AddContactActivity
import com.r2devpros.android_contactsapp.repository.RepositoryManager
import com.r2devpros.android_contactsapp.repository.local.room.ContactsRepo
import timber.log.Timber

class HomeActivity : AppCompatActivity() {
    private lateinit var layout: HomeActivityLayoutBinding
    private lateinit var rvContactsAdapter: RVContactsAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
        layoutBinding()
        bindViews()
        bindObservers()

        viewModel.getContacts()
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }

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

    private fun bindObservers() {
        Timber.d("HomeActivity_TAG: bindObservers: ")
        viewModel.availableContacts.observe(this) { contacts ->
            Timber.d("HomeActivity_TAG: bindObservers: availableContacts: ${contacts.size}")
            val tempList = contacts.map { contactItem ->
                ContactListItemViewModel().apply {
                    contact = contactItem
                }
            }

            rvContactsAdapter.itemList = tempList
        }
    }

    private fun initRecyclerView() {
        Timber.d("HomeActivity_TAG: initRecyclerView: ")
        rvContactsAdapter = RVContactsAdapter()
        layout.rvContacts.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
            adapter = rvContactsAdapter
        }
        rvContactsAdapter.itemList = emptyList()
    }

    private fun addContactClicked() {
        Timber.d("HomeActivity_TAG: addContactClicked: ")
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }
}