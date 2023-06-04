package com.fastporte.controller.fragments.Faq

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Faq

class FaqAdapter(var faqs: List<Faq>, val context: Context):
RecyclerView.Adapter<FaqAdapter.ViewHolder>(){

    class ViewHolder(val view: android.view.View): RecyclerView.ViewHolder(view) {
        val tvQuestion = view.findViewById<TextView>(com.fastporte.R.id.tvQuestion)
        val tvAnswer = view.findViewById<TextView>(com.fastporte.R.id.tvAnswer)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = android.view.LayoutInflater
            .from(parent.context)
            .inflate(com.fastporte.R.layout.prototype_faq, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faq = faqs[position]

        holder.tvQuestion.text = faq.question
        holder.tvAnswer.text = faq.answer
    }

    override fun getItemCount(): Int {
        return faqs.size
    }

}