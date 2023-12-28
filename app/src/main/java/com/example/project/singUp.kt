package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class singUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        var user = findViewById<TextInputEditText>(R.id.username)
        var password = findViewById<TextInputEditText>(R.id.password)
        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            if (user.text!!.isBlank()) {
                user.error = "User Name not less than 4 letters"
            }
            var email =  findViewById<TextInputEditText>(R.id.email)
            if(email.text!!.isBlank()){
               email.error ="plead enter a valid email"
           }
            if (password.text!!.isBlank()) {
                password.error = "please enter valid password"
            }
        }

        val txt =findViewById<TextView>(R.id.textView)
        txt.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}
