package com.example.deliveryapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {


    lateinit var editTextEmail: TextInputEditText
    lateinit var editTextPassword: TextInputEditText
    lateinit var buttonLogin: Button
    lateinit var auth: FirebaseAuth
    lateinit var processBar: ProgressBar
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonLogin = findViewById(R.id.btn_login)
        auth = FirebaseAuth.getInstance()
        processBar = findViewById<ProgressBar>(R.id.progressBar)
        textView = findViewById(R.id.registerNow)

        textView.setOnClickListener {
            val intent = Intent(this, Register::class.java)
        }
        buttonLogin.setOnClickListener(View.OnClickListener {
            processBar.visibility = View.VISIBLE
            val email: String
            val password: String
            email = editTextEmail.text.toString()
            password = editTextPassword.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this,"Enter email", Toast.LENGTH_SHORT).show()

            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this,"Enter password", Toast.LENGTH_SHORT).show()
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    processBar.visibility = View.GONE
                    if (task.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Authentication Successful.",
                            Toast.LENGTH_SHORT,
                        ).show()

                        val intent = Intent(this, Register::class.java)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        })
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, Register::class.java)
            finish()
        }
    }
}