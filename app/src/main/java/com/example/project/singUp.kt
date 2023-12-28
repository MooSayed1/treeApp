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

        val txt =findViewById<TextView>(R.id.textView)
        txt.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            //finish()
        }
        var user = findViewById<TextInputEditText>(R.id.username)
        var password: TextInputEditText = findViewById<TextInputEditText>(R.id.password)
        val loginkey = findViewById<Button>(R.id.signup)
        loginkey.setOnClickListener {

            if (user.text!!.isBlank()) {
                user.error = "please enter a valid user name"
            }
            val email = this.findViewById<TextInputEditText>(R.id.email)
            if(email.text!!.isBlank()){
               email.error ="please enter a valid email"
           }
            if (password.text!!.isBlank()) {
                password.error = "please enter valid password"
            }
            if(!(user.text!!.isBlank()&&email.text!!.isBlank()&&password.text!!.isBlank()))
            {
                //loginkey.setOnClickListener {
                    startActivity(Intent(this,feed::class.java))
               //}
            }
        }

    }
}
