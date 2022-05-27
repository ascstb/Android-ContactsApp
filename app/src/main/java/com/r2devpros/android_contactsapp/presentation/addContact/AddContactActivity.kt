package com.r2devpros.android_contactsapp.presentation.addContact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.r2devpros.android_contactsapp.R
import com.r2devpros.android_contactsapp.databinding.AddContactActivityLayoutBinding
import com.r2devpros.android_contactsapp.repository.RepositoryManager
import com.r2devpros.android_contactsapp.repository.local.room.ContactsRepo

class AddContactActivity : AppCompatActivity() {
    private lateinit var layout: AddContactActivityLayoutBinding
    private lateinit var viewModel: AddContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)

        layoutBinding()

        viewModel.getContacts()
    }

    private fun layoutBinding() {
        viewModel = AddContactViewModel(
            repositoryManager = RepositoryManager(
                ContactsRepo(this)
            )
        )

        layout = DataBindingUtil.setContentView(this, R.layout.add_contact_activity_layout)
        layout.lifecycleOwner = this
        layout.viewModel = viewModel
    }
}