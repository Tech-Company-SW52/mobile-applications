package com.fastporte.controller.fragments.ClientFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.fastporte.R
import com.fastporte.controller.fragments.Faq.FaqFragment

class ClientSupportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_client_support, container, false)

        val ivWebSite = view.findViewById<ImageView>(R.id.ivWebSite)
        val ivWhatsapp = view.findViewById<ImageView>(R.id.ivWhatsapp)
        val btFaq = view.findViewById<Button>(R.id.btFAQ)

        //Llevarme a un sitio web al hacer click en la imagen de la web
        ivWebSite.setOnClickListener {
            val url = "https://tech-company-sw52.github.io/landing-page/" // Reemplaza con el URL del sitio web externo
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        ivWhatsapp.setOnClickListener {
            val url = "https://wa.me/51981273313" // Reemplaza con el URL del sitio web externo
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        btFaq.setOnClickListener {
            //Al hacer click me lleva al fragment fragment_faq.xml
            val navController = findNavController()

            navController.navigate(R.id.id_client_faq_fragment)

        }

        return view
    }

}