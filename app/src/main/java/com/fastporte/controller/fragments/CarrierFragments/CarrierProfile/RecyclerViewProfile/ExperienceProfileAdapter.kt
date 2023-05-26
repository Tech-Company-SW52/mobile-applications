package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Experience
import com.fastporte.R

class ExperienceProfileAdapter(var experiences: ArrayList<Experience>) :
    RecyclerView.Adapter<ExperienceProfilePrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceProfilePrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_carrier_experience_profile, parent, false)
        return ExperienceProfilePrototype(view)
    }

    override fun onBindViewHolder(holder: ExperienceProfilePrototype, position: Int) {
        holder.bind(experiences.get(position))
    }

    override fun getItemCount(): Int {
        return experiences.size
    }

}


class ExperienceProfilePrototype(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
    val btJob = itemView.findViewById<Button>(R.id.btJob)
    val btTime = itemView.findViewById<TextView>(R.id.btTime)

    fun bind(experience: Experience) {
        btJob.text = experience.job
        btTime.text = experience.years
    }
}