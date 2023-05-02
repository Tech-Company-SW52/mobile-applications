package com.fastport.CarrierFragments.CarrierProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fastport.CarrierClasses.Comment
import com.fastport.CarrierFragments.CarrierProfile.RecyclerViewProfile.CommentProfileAdapter
import com.fastport.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarrierCommentsProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarrierCommentsProfileFragment : Fragment() {

    var comments = ArrayList<Comment>()
    var commentAdapter = CommentProfileAdapter(comments)

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_carrier_comments_profile, container, false)

        loadComments()
        initView(view)

        return view
    }

    private fun initView(view: View){
        val rvComments = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvCommentsCarrier)

        rvComments.adapter = commentAdapter
        rvComments.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(view.context)
    }

    private fun loadComments() {
        comments.add(
            Comment(
                1,
                "Muy buen servicio, muy amable y puntual",
                "Rosa Perez",
                5,
                "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGVyc29uYXxlbnwwfHwwfHw%3D&w=1000&q=80"
            )
        )
        comments.add(
            Comment(
                2,
                "Muy buen servicio, puntual",
                "Juan Perez",
                4,
                "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGVyc29uYXxlbnwwfHwwfHw%3D&w=1000&q=80"
            )
        )
        comments.add(
            Comment(
                3,
                "Muy buen servicio, muy amable",
                "Juan Perez",
                3,
                "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGVyc29uYXxlbnwwfHwwfHw%3D&w=1000&q=80"
            )
        )
        comments.add(
            Comment(
                4,
                "Muy buen servicio",
                "Juan Perez",
                2,
                "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGVyc29uYXxlbnwwfHwwfHw%3D&w=1000&q=80"
            )
        )
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarrierCommentsProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarrierCommentsProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}