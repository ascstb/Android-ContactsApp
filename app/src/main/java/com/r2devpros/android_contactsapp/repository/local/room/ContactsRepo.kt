package com.r2devpros.android_contactsapp.repository.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.r2devpros.android_contactsapp.model.Contact

class ContactsRepo(context: Context) {
    private val contactsDAO: ContactsDAO

    init {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'contact' ADD COLUMN photo TEXT NOT NULL DEFAULT ''")
            }
        }

        val contactsDatabase =
            Room.databaseBuilder(
                context,
                ContactsDatabase::class.java,
                "contacts-database"
            )
                .addMigrations(MIGRATION_1_2)
                .build()

        contactsDAO = contactsDatabase.contactsDAO()
    }

    fun getContacts(): List<Contact> = contactsDAO.getContacts()

    fun addContact(contact: Contact) = contactsDAO.addContact(contact)

    fun deleteContactById(contactId: String) = contactsDAO.deleteContactById(contactId)
}