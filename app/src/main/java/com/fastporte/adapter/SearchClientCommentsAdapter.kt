package com.fastporte.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.Comment
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SearchClientCommentsAdapter(val comments: List<Comment>, val context: Context) :
    RecyclerView.Adapter<SearchClientCommentsAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val iv_search_comment_profile =
            view.findViewById<CircleImageView>(R.id.iv_search_comment_profile)
        val tv_search_comment_name = view.findViewById<TextView>(R.id.tv_search_comment_name)
        val tv_search_comment_comment = view.findViewById<TextView>(R.id.tv_search_comment_comment)
        val tv_search_comment_start_puntuation =
            view.findViewById<TextView>(R.id.tv_search_comment_start_puntuation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_client_search_profile_comments, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        holder.tv_search_comment_name.text = comment.client.name
        holder.tv_search_comment_comment.text = comment.comment
        holder.tv_search_comment_start_puntuation.text = comment.star.toString()

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader((OkHttp3Downloader(context)))
        picBuilder.build().load(comment.client.photo).placeholder(R.color.white)
            .error(R.color.white).into(holder.iv_search_comment_profile)
    }

}