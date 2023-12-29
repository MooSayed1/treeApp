package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var islogend:Boolean=false
        val sharedPref = getSharedPreferences("myPreferences", MODE_PRIVATE)
        islogend=sharedPref.getBoolean("islogend",false)

        if(islogend) {
            startActivity(Intent(this, feed::class.java))
            finish()
        }
        val txt = findViewById<TextView>(R.id.textView)
        txt.setOnClickListener {
            startActivity(Intent(this, singUp::class.java))
            finish()
        }

        var user = findViewById<TextInputEditText>(R.id.user)
        var password = findViewById<TextInputEditText>(R.id.password)
        val btn = findViewById<Button>(R.id.signup)
        btn.setOnClickListener {
            if (user.text!!.isBlank() ) {
                user.error = "User Name not less than 4 letters"
            }

            if (password.text!!.isBlank()) {
                password.error = "please enter valid password"
            }

            if (!(user.text!!.isBlank() && password.text!!.isBlank())) {

                val sharedPref = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("islogend", true) // Example for a string value
                editor.apply() // Apply changes asynchronously

                startActivity(Intent(this, feed::class.java))
                finish()
            }


        }
    }
}