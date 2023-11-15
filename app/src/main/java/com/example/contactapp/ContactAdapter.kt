package com.example.contactapp

import android.app.Activity
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contacts: ArrayList<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var longPressedPosition: Int = -1

    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val avatar: TextView = view.findViewById(R.id.avatar)
        val name: TextView = view.findViewById(R.id.name)

        init {
            itemView.setOnCreateContextMenuListener(this)
            itemView.setOnLongClickListener {
                longPressedPosition = adapterPosition
                false
            }
        }

        override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
            val inflater = (v.context as Activity).menuInflater
            inflater.inflate(R.menu.context_menu, menu)
            val contact = contacts[adapterPosition]
            menu.findItem(R.id.call).title = "Call ${contact.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        (parent.context as Activity).registerForContextMenu(itemView)
        return ContactViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contacts[position]
        holder.avatar.text = currentContact.name.first().toString()
        holder.name.text = currentContact.name

        holder.itemView.setOnClickListener { view ->
            val detailIntent = Intent(view.context, DetailsActivity::class.java).apply {
                putExtra("ID", currentContact.id)
                putExtra("NAME", currentContact.name)
                putExtra("PHONE", currentContact.phone)
                putExtra("EMAIL", currentContact.email)
                putExtra("AVATAR", holder.avatar.text.toString())
            }
            view.context.startActivity(detailIntent)
        }

        holder.itemView.setOnLongClickListener { view ->
            view.showContextMenu()
            true
        }
    }

    override fun getItemCount() = contacts.size
}
