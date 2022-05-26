package com.r2devpros.android_contactsapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "contact")
data class Contact(
    @NonNull
    @PrimaryKey
    var contactId: String = UUID.randomUUID().toString(),
    var name: String,
    var lastName: String,
    var genre: String,
    var age: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(contactId)
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeString(genre)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}