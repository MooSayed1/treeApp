package com.example.project


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class feed : AppCompatActivity() {
    lateinit var list: ArrayList<Post>
    lateinit var adapter: adapter
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        getDate()

        list = ArrayList()

        list.apply {
            add(
                Post(
                    id=1 , views = 2 , Content = "Hello i'm mohamed", age = 20, username = "Mohamed", handel = "#lmalsdj123", likes = 90,
                    image = "https://i.pinimg.com/474x/27/03/12/270312b769ec73b030b1abd98a7209ef.jpg"
                )
            )

            add(
                Post(
                    id=1 , views = 2 , Content = "Fuck microsoft", age = 20, username = "Ezz", handel = "@saldf21", likes = 88,
                    image = "https://i.pinimg.com/474x/27/03/12/270312b769ec73b030b1abd98a7209ef.jpg"
                )
            )
            add(
                Post(
                    id=1 , views = 2 , Content = "welcome to our fk app", age = 20, username = "ghrapawy", handel = "ljska", likes = 73,
                    image = "https://i.pinimg.com/474x/27/03/12/270312b769ec73b030b1abd98a7209ef.jpg"
                )
            )
        }

        adapter = adapter(list,this)
        recycler = findViewById(R.id.recycler)
        recycler.adapter = adapter


    }
    fun getDate(){
       val url="http://json.almiraj.xyz/jsonProfile/GH"
        val request=Request.Builder().url(url).build()
        val client=OkHttpClient()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {

                println("faliiiieeddd")
            }

            override fun onResponse(call: Call, response: Response) {
                val responsebody= response.body?.string()
                println(responsebody)
            }

        })
    }
}