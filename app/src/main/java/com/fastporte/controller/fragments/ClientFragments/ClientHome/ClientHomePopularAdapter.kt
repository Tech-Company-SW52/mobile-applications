package com.fastporte.controller.fragments.ClientFragments.ClientHome

import android.content.Context
import android.os.Build.VERSION_CODES.P
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.controller.fragments.CarrierFragments.CarrierHome.CarrierHomeAdapter
import com.fastporte.database.PopularDriverDB
import com.fastporte.models.Driver
import com.fastporte.models.PopularDriver
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class ClientHomePopularAdapter(var drivers: List<Driver>, val context: Context) :
    RecyclerView.Adapter<ClientHomePopularAdapter.ViewHolder>() {
    class ViewHolder(val view: android.view.View) : RecyclerView.ViewHolder(view) {
        val cvPhoto =
            view.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.civDriverProfile)
        val tvName = view.findViewById<android.widget.TextView>(R.id.tvDriverName)

        val fabFavorite = view.findViewById<FloatingActionButton>(R.id.fabFavourite)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_client_popular_home, parent, false)
        return ClientHomePopularAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val driver = drivers[position]

        if (PopularDriverDB.getInstance(this.context).getPopularDriverDAO().existsPopularDriver(driver.id)) {
            holder.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }


        holder.tvName.text = driver.name + " " + driver.lastname
        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build()
            .load(driver.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.cvPhoto)

        holder.fabFavorite.setOnClickListener {

            if(PopularDriverDB.getInstance(this.context).getPopularDriverDAO().existsPopularDriver(driver.id)){
                PopularDriverDB.getInstance(this.context).getPopularDriverDAO().deletePopularDriver(driver)
                holder.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
            }
            else{
                PopularDriverDB.getInstance(this.context).getPopularDriverDAO().insertPopularDriver(driver)
                holder.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
            }

        }

    }

    override fun getItemCount(): Int {
        return 5
    }

}