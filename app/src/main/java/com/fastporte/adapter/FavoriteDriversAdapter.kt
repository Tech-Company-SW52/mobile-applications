package com.fastporte.adapter
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.database.PopularDriverDB
import com.fastporte.models.Driver
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FavoriteDriversAdapter(private val favoriteDrivers: List<Driver>,
                             private val context: Context): RecyclerView.Adapter<FavoriteDriversAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val cvPhoto = view.findViewById<CircleImageView>(R.id.civDriverProfile)
        val tvName = view.findViewById<TextView>(R.id.tvDriverName)
        val fabDelete = view.findViewById<FloatingActionButton>(R.id.fabDeleteDriver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_favorite_driver, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favDriver = favoriteDrivers[position]
        holder.fabDelete.setImageResource(R.drawable.baseline_delete_24)

        holder.tvName.text = favDriver.name + " " + favDriver.lastname
        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build()
            .load(favDriver.photo)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.cvPhoto)

        holder.fabDelete.setOnClickListener{

            if(PopularDriverDB.getInstance(this.context).getPopularDriverDAO().existsPopularDriver(favDriver.id)){
                PopularDriverDB.getInstance(this.context).getPopularDriverDAO().deletePopularDriver(favDriver)
                holder.fabDelete.setImageResource(R.drawable.baseline_favorite_border_16)
            }
            else{
                PopularDriverDB.getInstance(this.context).getPopularDriverDAO().insertPopularDriver(favDriver)
                holder.fabDelete.setImageResource(R.drawable.baseline_delete_24)
            }

        }
    }

    override fun getItemCount(): Int {
        return favoriteDrivers.size
    }


}
