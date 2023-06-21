package com.fastporte.controller.fragments.CarrierFragments.CarrierProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.models.Comment
import com.fastporte.R
import com.fastporte.controller.fragments.CarrierFragments.CarrierProfile.RecyclerViewProfile.CommentProfileAdapter
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.network.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarrierCommentsProfileFragment : Fragment() {

    lateinit var commentRecyclerView: RecyclerView
//    var comments = ArrayList<Comment>()
//    var commentAdapter = CommentProfileAdapter(comments)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View =
            inflater.inflate(R.layout.fragment_carrier_comments_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commentRecyclerView = view.findViewById(R.id.rvCommentsCarrier)
        loadComments(view)
    }

    private fun loadComments(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL.BASE_URL.toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val commentService: ProfileService = retrofit.create(ProfileService::class.java)

        val request = commentService.getDriverComments(
            SharedPreferences(view.context).getValue("id")!!.toInt(), "json"
        )

        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    if (response.message() == "No Content") {
                        Toast.makeText(view.context, "No hay comentarios", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val comments: List<Comment> = response.body()!!
                        commentRecyclerView.layoutManager = LinearLayoutManager(view.context)
                        commentRecyclerView.adapter = CommentProfileAdapter(comments, view.context)

                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

}