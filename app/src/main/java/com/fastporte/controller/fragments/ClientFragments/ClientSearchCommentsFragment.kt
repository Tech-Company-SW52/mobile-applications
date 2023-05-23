package com.fastporte.controller.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fastporte.R
import com.fastporte.adapter.SearchClientCommentsAdapter
import com.fastporte.models.CommentsSearch
import com.fastporte.network.ClientsService
import com.fastporte.network.CommentsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientSearchCommentsFragment : Fragment() {
    lateinit var commentRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_client_search_comments, container, false)

        commentRecyclerView=view.findViewById(R.id.rv_client_search_comments)
        loadComments(view)

        return view
    }

    private fun loadComments(view: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-fastporte.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val commentService:CommentsService= retrofit.create(CommentsService::class.java)
        val listComments = commentService.getCommentsByDriverID(1)
        listComments.enqueue(object :Callback<List<CommentsSearch>>{
            override fun onResponse(
                call: Call<List<CommentsSearch>>,
                response: Response<List<CommentsSearch>>
            ) {
                val commentList=response.body()
                if(commentList!=null){
                    commentRecyclerView.adapter= SearchClientCommentsAdapter(commentList,requireContext())
                    commentRecyclerView.layoutManager= LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<List<CommentsSearch>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}