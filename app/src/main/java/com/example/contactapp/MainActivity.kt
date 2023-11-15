package com.example.contactapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Contact(
    val id: String,
    val name: String,
    val phone: String,
    val email: String
)

class MainActivity : AppCompatActivity() {
    private lateinit var data: ArrayList<Contact>
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = arrayListOf(
            Contact("1", "John Doe", "+1234567890", "johndoe@example.com"),
            Contact("2", "Jane Smith", "+0987654321", "janesmith@example.com"),
            Contact("3", "Bob Johnson", "+1122334455", "bobjohnson@example.com"),
            Contact("4", "Alice Williams", "+5566778899", "alicewilliams@example.com"),
            Contact("5", "Charlie Brown", "+9988776655", "charliebrown@example.com")
        )

        adapter = ContactAdapter(data)

        val recyclerView: RecyclerView = findViewById(R.id.contact_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = adapter.longPressedPosition
        val contact = data[position]
        when (item.itemId) {
            R.id.call -> {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${contact.phone}")
                }
                startActivity(intent)
                return true
            }
            R.id.sms -> {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("sms:${contact.phone}")
                }
                startActivity(intent)
                return true
            }
            R.id.email -> {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${contact.email}")
                }
                startActivity(intent)
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }
}

