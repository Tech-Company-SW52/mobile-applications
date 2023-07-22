package com.fastporte.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.models.Experience
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SearchClientExperienceAdapter(val experiences: List<Experience>, val context: Context) :
    RecyclerView.Adapter<SearchClientExperienceAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvSearchClientJob = view.findViewById<TextView>(R.id.tvSearchClientJob)
        val tvSearchClientJobTime = view.findViewById<TextView>(R.id.tvSearchClientJobTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_client_search_profile_experience, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return experiences.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val experience = experiences[position]
        holder.tvSearchClientJob.text = experience.job
        holder.tvSearchClientJobTime.text = (experience.years.toString()) + " years"
    }
}