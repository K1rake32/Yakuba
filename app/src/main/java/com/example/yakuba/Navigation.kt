package com.example.yakuba

import androidx.navigation.NavController

object NavigationFragment {
    fun NavigationAuth (navController: NavController) {
        navController.navigate(R.id.action_mainRegisterFragment_to_nubmerConfirmationFragment)
    }

        fun NavigationInf(navController: NavController) {
        navController.navigate(R.id.action_nubmerConfirmationFragment_to_userInformationFragment)
    }

        fun NavigationMain(navController: NavController) {
            navController.navigate(R.id.action_userInformationFragment_to_homeFragment)
        }

        fun NavgationDetails(navController: NavController) {
            navController.navigate(R.id.action_homeFragment_to_fragmentDetails)
        }

        fun NavigationEdit(navController: NavController) {
            navController.navigate(R.id.action_userFragment_to_userEditFragment)
        }

        fun NavigationUserDelete(navController: NavController) {
            navController.navigate(R.id.action_userFragment_to_mainRegisterFragment)
        }

        fun NavigationDataSave(navController: NavController) {
            navController.navigate(R.id.action_userEditFragment_to_userFragment)
        }


    fun NavigationUserDelete2(navController: NavController) {
        navController.navigate(R.id.action_userEditFragment_to_mainRegisterFragment)
    }

    fun NavigationCreatePost(navController: NavController) {
        navController.navigate(R.id.action_homeFragment_to_addPostPhotoFragment)
    }
}