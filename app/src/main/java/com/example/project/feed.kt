package com.example.project


import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

class feed : AppCompatActivity() {
    lateinit var list: ArrayList<Post>
    lateinit var adapter: adapter
    lateinit var recycler: RecyclerView
    lateinit var client: OkHttpClient

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        client= OkHttpClient()

        list = ArrayList()
        adapter = adapter(list,this)
        recycler = findViewById(R.id.recycler)
        recycler.adapter = adapter
        lifecycleScope.launch (Dispatchers.IO){ getData() }
        val swipeRefreshLayout:SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

                swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val newData = getData() // Fetch new data
                    withContext(Dispatchers.Main) {
                        // Update UI elements with the new data
                        getData()
                        adapter.notifyDataSetChanged()
                        swipeRefreshLayout.isRefreshing=false
                    }
                } catch (e: Exception) {
                    // Handle errors gracefully
                    withContext(Dispatchers.Main) {
                        // Display an error message to the user
                        swipeRefreshLayout.isRefreshing = false // Stop the refresh animation
                    }
                }
            }
        }


    }
    fun getData() {
        val tempList = ArrayList<Post>() // Temporary list to store data

        var link = "http://json.almiraj.xyz/jsonfeed"
        val request = Request.Builder()
            .url(link)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("DATATAG", e.message.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val jsonfile = JSONObject(body).getJSONArray("Posts")
                for (x in 0 until jsonfile.length()) {
                    val data = jsonfile.getJSONObject(x)
                    val views = data.getInt("views")
                    val Photo = data.getString("Photo")
                    val Content = data.getString("Content")
                    val likes = data.getInt("likes")
                    val User_Name = data.getString("User_Name")
                    val Handle = data.getString("Handle")
                    val Date = data.getInt("Date")
                    val id = data.getInt("id")
                    tempList.add(Post(views, id, Content, User_Name, Handle, likes, Photo, Date))
                }

                // Update UI on the main thread
                lifecycleScope.launch (Dispatchers.Main){
                    list.addAll(tempList)
                    adapter.notifyDataSetChanged()
                }

            }

        })
        Log.d("DATATAG", list.toString())
    }
}
