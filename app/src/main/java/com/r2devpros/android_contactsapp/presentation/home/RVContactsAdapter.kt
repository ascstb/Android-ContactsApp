package com.r2devpros.android_contactsapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.r2devpros.android_contactsapp.databinding.ContactListItemLayoutBinding
import timber.log.Timber

class RVContactsAdapter : RecyclerView.Adapter<RVContactsAdapter.ViewHolder>() {
    var itemList: List<ContactListItemViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(val layout: ContactListItemLayoutBinding) :
        RecyclerView.ViewHolder(layout.root) {
        fun bind() {
            Timber.d("ViewHolder_TAG: bind: ")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ContactListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run {
                ViewHolder(this)
            }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layout.viewModel = itemList[position]
        holder.bind()
        holder.layout.executePendingBindings()
    }

    override fun getItemCount(): Int = itemList.size
}