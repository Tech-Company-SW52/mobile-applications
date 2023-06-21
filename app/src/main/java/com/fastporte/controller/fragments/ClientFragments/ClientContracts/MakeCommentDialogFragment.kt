package com.fastporte.controller.fragments.ClientFragments.ClientContracts

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.fastporte.databinding.FragmentMakeCommentDialogBinding
import com.fastporte.helpers.BaseURL
import com.fastporte.helpers.SharedPreferences
import com.fastporte.models.Comment
import com.fastporte.models.Contract
import com.fastporte.network.CommentsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MakeCommentDialogFragment(val contract: Contract) : DialogFragment() {
    private lateinit var binding: FragmentMakeCommentDialogBinding
    val bundle = Bundle()
    lateinit var comment: Comment

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BaseURL.BASE_URL.toString())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val commentsService: CommentsService = retrofit.create(CommentsService::class.java)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentMakeCommentDialogBinding.inflate(layoutInflater)
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        builder.setPositiveButton("Update") { _, _ ->
            val myComment = binding.tilComment.editText?.text.toString()
            val stars = binding.rbStars.rating.toString()
            val numStars = stars.toFloat()

            comment = Comment(0, myComment, numStars, contract.client)

            postComment()

            dismiss()
        }

        builder.setNegativeButton("Cancel") { _, _ ->
            dismiss()
        }

        val dialog = builder.create()
        dialog.show()

        return dialog
    }

    private fun postComment() {
        val request = commentsService.postComment(
            SharedPreferences(this.requireContext()).getValue("id")!!.toInt(),
            contract.driver.id,
            this.comment
        )

        request.enqueue(object : Callback<Comment> {
            override fun onFailure(call: Call<Comment>, t: Throwable) {
                Log.d("Error", t.toString())
            }

            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                if (response.isSuccessful) {
                    Log.d("Activity success", response.body().toString())
                } else {
                    Log.d("Activity fail", response.errorBody().toString())
                }
            }
        })
    }
}