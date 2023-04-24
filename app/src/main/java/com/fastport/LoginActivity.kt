package com.fastport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class LoginActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnClient=findViewById<Button>(R.id.btnClient)
        val btnCarrier=findViewById<Button>(R.id.btnCarrier)

        val clientIntent= Intent(this, ClientActivity::class.java)
        val carrierIntent= Intent(this,CarrierActivity::class.java)

        toolbar=findViewById(R.id.myPreToolBar)
        setSupportActionBar(toolbar)

        btnClient.setOnClickListener(){
            startActivity(clientIntent)
        }
        btnCarrier.setOnClickListener(){
            startActivity(carrierIntent)
        }
    }
}