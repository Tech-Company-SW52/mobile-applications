package com.fastporte.controller.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fastporte.R
import com.fastporte.helpers.SharedPreferences
import com.google.android.material.navigation.NavigationView


class ClientActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        val logout = findViewById<TextView>(R.id.toolbar_user_initial)

        logout.text = getUserInitials()

        logout.setOnClickListener {

            val popupMenu = PopupMenu(this, logout)
            popupMenu.inflate(R.menu.logout_menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.logout -> {
                        Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
                        SharedPreferences(this).clear()
                        finish()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        toolbar = findViewById(R.id.myToolBar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.client_drawer)
        navigationView = findViewById(R.id.clientNavigationView)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.clientFragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.id_client_home_fragment,
                R.id.id_client_contracts_fragment,
                R.id.id_client_profile_fragment,
                R.id.id_client_support_fragment,
                R.id.id_client_search_fragment,
                R.id.id_client_notifications_fragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, drawerLayout)
        navigationView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.clientFragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun getUserInitials(): String {
        val fullName = SharedPreferences(this).getValue("fullName")
        val name = fullName!!.split(" ")

        return "${name[0][0]}${name[1][0]}"
    }
}