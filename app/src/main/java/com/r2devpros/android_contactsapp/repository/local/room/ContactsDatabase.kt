package com.r2devpros.android_contactsapp.repository.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.r2devpros.android_contactsapp.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun contactsDAO(): ContactsDAO
}