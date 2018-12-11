package com.ignes.draftfragments

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        val navController = host.navController
        setupActionBar(navController)

        setupNavigationMenu(navController)
    }

    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
    }

    private fun setupActionBar(navController: NavController) {
        /*
        This allows NavigationUI to decide what label to show in the action bar
        And, since we are passing in drawerLayout, it will also determine whether to
        show the up arrow or drawer menu icon
        - Show a title in the ActionBar, based off of the destination's label
        - Display the Up button when you are on any destination that's not the starting destination
        - Display a drawer icon (hamburger icon) when you're on the start destination
         */
        drawerLayout = findViewById(R.id.drawer_layout)
        setupActionBarWithNavController(navController, drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        /*
        Allows NavigationUI to support proper up navigation or the drawer layout
        drawer menu, depending on the situation
         */
        // TODO: check if it works
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(drawerLayout)
        //return drawerLayout.navigateUp(findNavController(R.id.nav_host_fragment))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        // The NavigationView already has these same navigation items, so we only add
        // navigation items to the menu here if there isn't a NavigationView
        if (navigationView == null) {
            menuInflater.inflate(R.menu.activity_main_drawer, menu)
            return true
        }
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*
        Navigation UI Handle the item selection:
        Have the NavigationUI look for an action or destination matching the menu
        item id and navigate there if found.
        Otherwise, bubble up to the parent.
         */
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
            || super.onOptionsItemSelected(item)
    }
}