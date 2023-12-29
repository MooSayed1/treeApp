package com.example.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.internal.notify

class adapter(var list: ArrayList<Post>, val context: Context) :
    RecyclerView.Adapter<adapter.viewholder>() {
    class viewholder(item: View) : RecyclerView.ViewHolder(item) {
        val name = item.findViewById<TextView>(R.id.username)
        val handel = item.findViewById<TextView>(R.id.handel)
        val likes = item.findViewById<TextView>(R.id.likes)
        val shares = item.findViewById<TextView>(R.id.shares)
        val content = item.findViewById<TextView>(R.id.content)
        val image = item.findViewById<ImageView>(R.id.image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
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

        }
        holder.itemView.setOnClickListener {
            Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshData(newList: ArrayList<Post>){
        list=newList
        notifyDataSetChanged()
    }
}
