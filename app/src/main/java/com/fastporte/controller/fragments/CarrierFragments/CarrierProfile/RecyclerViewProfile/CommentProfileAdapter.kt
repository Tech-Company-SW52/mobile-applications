package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Comment
import com.fastporte.R
import com.squareup.picasso.Picasso

class CommentProfileAdapter(var comments: ArrayList<Comment>) :
    RecyclerView.Adapter<CommentProfilePrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentProfilePrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_comment_profile, parent, false)
        return CommentProfilePrototype(view)
    }

    override fun onBindViewHolder(holder: CommentProfilePrototype, position: Int) {
        holder.bind(comments.get(position))
    }

    override fun getItemCount(): Int {
        return comments.size
    }

}

class CommentProfilePrototype(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvComment = itemView.findViewById<TextView>(R.id.tvDescription)
    val tvRate = itemView.findViewById<TextView>(R.id.tvCarrierRate)
    val ivPhotoComment = itemView.findViewById<ImageView>(R.id.civProfileImage)

    fun bind(comment: Comment) {
        tvName.text = comment.name
        tvComment.text = comment.comment
        tvRate.text = comment.rate.toString()

        Picasso.get().load(comment.urlImage)
            .error(R.mipmap.ic_launcher_round)
            .into(ivPhotoComment)
    }

}