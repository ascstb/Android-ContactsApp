package com.r2devpros.android_contactsapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r2devpros.android_contactsapp.databinding.ContactListItemLayoutBinding
import com.r2devpros.android_contactsapp.model.Contact

class RVContactsAdapter(
    private val listener: (Contact) -> Unit
) : RecyclerView.Adapter<RVContactsAdapter.ViewHolder>() {
    var itemList: List<ContactListItemViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val layout: ContactListItemLayoutBinding) :
        RecyclerView.ViewHolder(layout.root) {
        fun bind(contact: Contact, listener: (Contact) -> Unit) {
            itemView.setOnClickListener {
                listener(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ContactListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run {
                ViewHolder(this)
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactItemViewModel = itemList[position]
        holder.layout.viewModel = contactItemViewModel
        contactItemViewModel.contact?.let { contact ->
            holder.bind(contact, listener)
        }
        holder.layout.executePendingBindings()
    }

    override fun getItemCount(): Int = itemList.size
}