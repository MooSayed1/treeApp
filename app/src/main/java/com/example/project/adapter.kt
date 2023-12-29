package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast.*
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R.layout.activity_item_tweet
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Suppress("NAME_SHADOWING")
class adapter(var list: ArrayList<Post>, val context: Context) :
    RecyclerView.Adapter<adapter.viewholder>() {
    class viewholder(item_tweet: View) : RecyclerView.ViewHolder(item_tweet) {
        val name = item_tweet.findViewById<TextView>(R.id.tweetUsername)
        val handel = item_tweet.findViewById<TextView>(R.id.tweet_handel)
        val likes = item_tweet.findViewById<TextView>(R.id.tweetLikeCount)
        val shares = item_tweet.findViewById<TextView>(R.id.tweetRetweetCount)// Number of shares
        val content = item_tweet.findViewById<TextView>(R.id.tweet_content)
        val date = item_tweet.findViewById<TextView>(R.id.tweetDate)
        val image: ImageView = item_tweet.findViewById<ImageView>(R.id.tweet_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context).inflate(activity_item_tweet, parent, false)
        return viewholder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val position = list.get(position)
        holder.apply {
//            Glide.with(context).load(position.Photo).into(image)
            shares.text = position.views.toString()
            name.text = position.User_Name
            handel.text = position.Handle
            content.text = position.Content
            likes.text = position.likes.toString()
            date.text=convertUnixTimestampToString(position.Date.toLong())
            //date.text=position.Date.toString()

        }
        holder.itemView.setOnClickListener {
            makeText(context,"hello", LENGTH_SHORT).show()

        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newList: ArrayList<Post>){
        list=newList
        notifyDataSetChanged()
    }
    fun convertUnixTimestampToString(unixTimestamp: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getDefault()

        val date = Date(unixTimestamp * 1000L)
        return dateFormat.format(date)
    }
}
