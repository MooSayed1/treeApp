package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import java.io.IOException

class feed : AppCompatActivity() {
    lateinit var list: ArrayList<Post>
    lateinit var adapter: adapter
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                .url(url)
                .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
            }

        list = ArrayList()

        list.apply {
            add(
                Post(
                    id=1 , views = 2 , Content = "Hello i'm mohamed", age = 20, username = "Mohamed", handel = "#lmalsdj123", likes = 90, image = ""
                )
            )
        }

        adapter = adapter(list,this)
        recycler = findViewById(R.id.recycler)
        recycler.adapter = adapter


    }
}