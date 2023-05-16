package com.fastporte.controller.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.google.android.material.navigation.NavigationView

class CarrierActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrier)

        toolbar = findViewById(R.id.myToolBar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.carrier_drawer)
        navigationView = findViewById(R.id.carrierNavigationView)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.carrierFragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.id_carrier_home_fragment,
                R.id.id_carrier_contracts_fragment,
                R.id.id_carrier_profile_fragment,
                R.id.id_carrier_support_fragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, drawerLayout)
        navigationView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.carrierFragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}