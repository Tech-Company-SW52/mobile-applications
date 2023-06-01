package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Comment
import com.fastporte.R
import com.squareup.picasso.Picasso

class CommentProfileAdapter(var comments: List<Comment>, val context: Context) :
    RecyclerView.Adapter<CommentProfileAdapter.ViewHolder>() {

    class ViewHolder(val view: android.view.View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvCommentName)
        val tvComment = view.findViewById<TextView>(R.id.tvComment)
        val tvRate = view.findViewById<TextView>(R.id.tvCarrierRate)
        val ivPhotoComment = view.findViewById<ImageView>(R.id.civProfileImage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_comment_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]

        holder.tvName.text = comment.client.name
        holder.tvComment.text = comment.comment
        holder.tvRate.text = comment.star.toString()

        Picasso.get()
            .load(comment.client.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.ivPhotoComment)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

}