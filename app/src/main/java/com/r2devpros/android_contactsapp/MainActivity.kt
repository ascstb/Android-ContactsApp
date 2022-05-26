package com.r2devpros.android_contactsapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.r2devpros.android_contactsapp.model.Contact
import com.r2devpros.android_contactsapp.repository.RepositoryManager
import com.r2devpros.android_contactsapp.repository.local.room.ContactsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var repositoryManager: RepositoryManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryManager = RepositoryManager(
            ContactsRepo(this)
        )

        CoroutineScope(Dispatchers.IO).launch {
            val result = repositoryManager.getContacts()
            Timber.d("MainActivity_TAG: onCreate: contacts: ${result.size}")
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            onAddClicked()
        }
    }

    private fun onAddClicked() {
        Timber.d("MainActivity_TAG: onAddClicked: ")
        val name = findViewById<TextInputEditText>(R.id.tvName).text.toString()
        val lastName = findViewById<TextInputEditText>(R.id.tvLastName).text.toString()
        val genre = findViewById<TextInputEditText>(R.id.tvGenre).text.toString()
        val age = findViewById<TextInputEditText>(R.id.tvAge).text.toString()

        val contact = Contact(
            name = name,
            lastName = lastName,
            genre = genre,
            age = age.toIntOrNull() ?: 0
        )

        CoroutineScope(Dispatchers.IO).launch {
            repositoryManager.addContact(contact)
        }
    }
}