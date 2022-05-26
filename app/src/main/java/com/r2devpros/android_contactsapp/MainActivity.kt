package com.r2devpros.android_contactsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }
}