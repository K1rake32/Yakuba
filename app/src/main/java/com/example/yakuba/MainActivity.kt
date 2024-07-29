package com.example.yakuba

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.yakuba.UI.UI.Auth.MainRegisterFragment
import com.example.yakuba.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val dataModel: DataModel by viewModels()
    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_Navigation)
        MAIN = this



        val bottomNavigationView = binding.bottonNavigation

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.shop -> {
                    navController.navigate(R.id.shopFragment)
                    true
                }
                R.id.gal -> {
                    navController.navigate(R.id.galleryFragment)
                    true
                }
                R.id.user -> {
                    navController.navigate(R.id.userFragment)
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainRegisterFragment,
                R.id.nubmerConfirmationFragment,
                R.id.fragmentDetails,
                R.id.addPostPhotoFragment,
                R.id.userEditFragment,
                R.id.userInformationFragment -> bottomNavigationView.visibility = View.GONE
                else -> bottomNavigationView.visibility = View.VISIBLE
            }
        }



        dataModel.message.observe(this, {})
    }


    override fun onBackPressed() {
        val currentDestination = navController.currentDestination

        if (currentDestination?.id == R.id.mainRegisterFragment) {
            finishAffinity()
        } else {
            if (!navController.popBackStack()) {
                super.onBackPressed()
            }
        }
    }


}