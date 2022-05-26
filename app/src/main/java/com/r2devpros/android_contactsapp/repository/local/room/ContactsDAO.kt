package com.r2devpros.android_contactsapp.repository.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.r2devpros.android_contactsapp.model.Contact

@Dao
interface ContactsDAO {
    @Query("SELECT * FROM contact")
    fun getContacts(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContact(contact: Contact)

    @Query("DELETE FROM contact WHERE contactId = :contactId")
    fun deleteContactById(contactId: String)
}