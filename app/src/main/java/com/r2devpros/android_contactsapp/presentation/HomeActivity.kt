package com.r2devpros.android_contactsapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.r2devpros.android_contactsapp.R
import com.r2devpros.android_contactsapp.databinding.HomeActivityLayoutBinding
import com.r2devpros.android_contactsapp.presentation.addContact.AddContactActivity
import timber.log.Timber

class HomeActivity : AppCompatActivity() {
    private lateinit var layout: HomeActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
        layoutBinding()

        bindViews()
    }

    private fun layoutBinding() {
        Timber.d("HomeActivity_TAG: layoutBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.home_activity_layout)
        layout.lifecycleOwner = this
    }

    private fun bindViews() {
        layout.btnGoToAdd.setOnClickListener {
            addContactClicked()
        }
    }

    private fun addContactClicked() {
        Timber.d("HomeActivity_TAG: addContactClicked: ")
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }
}