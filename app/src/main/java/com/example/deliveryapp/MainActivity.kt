package com.example.deliveryapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var button: Button
    lateinit var textView: TextView
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        button = findViewById<Button>(R.id.logout)
        textView = findViewById(R.id.user_details)
        user = auth.currentUser
        if (user == null) {
            val intent = Intent(this, Login::class.java)
            finish()
        }else {
            textView.setText(user!!.email)
        }
        button.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Login::class.java)
            finish()
        })
    }
}