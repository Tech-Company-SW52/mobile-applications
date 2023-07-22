package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Experience
import com.fastporte.R
import java.sql.Time
import java.time.LocalTime


class ExperienceProfileAdapter(var experiences: List<Experience>, val context: Context) :
    RecyclerView.Adapter<ExperienceProfileAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val btJob = view.findViewById<Button>(R.id.btJob)
        val btTime = view.findViewById<TextView>(R.id.btTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_experience_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val experience = experiences[position]

        holder.btJob.text = experience.job
        holder.btTime.text = experience.years.toString()

    }

    override fun getItemCount(): Int {
        return experiences.size
    }


}