package com.example.yakuba.UI.UI.Main.profile

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.yakuba.DataModel
import com.example.yakuba.MAIN
import com.example.yakuba.NavigationFragment
import com.example.yakuba.R
import com.example.yakuba.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editProfile()
        leaveInProfile()
        nameEdit()
        vkWakeup()
        feedbackProfile()
    }

    private fun editProfile() {
        with(binding) {
            editProfile.setOnClickListener {
                NavigationFragment.NavigationEdit(MAIN.navController)
            }
        }
    }

    private fun leaveInProfile() {
        with(binding) {
            closeProfile.setOnClickListener() {
                val dialogBinding = layoutInflater.inflate(R.layout.leave_dialog_item, null)
                val myDialog = Dialog(requireContext())
                myDialog.setContentView(dialogBinding)
                myDialog.setCancelable(true)
                myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                myDialog.show()

                val dialogCancel = dialogBinding.findViewById<TextView>(R.id.dialogCancel)
                val dialogDelete = dialogBinding.findViewById<TextView>(R.id.dialogDelete)

                dialogCancel.setOnClickListener() {
                    myDialog.dismiss()
                }

                dialogDelete.setOnClickListener() {
                    NavigationFragment.NavigationUserDelete(MAIN.navController)
                    myDialog.dismiss()
                }

            }
        }

    }

    private fun nameEdit() {

        with(binding) {
                dataModel.name.observe(activity as LifecycleOwner, {
                    nameEdit.text = it
                })

                dataModel.sername.observe(activity as LifecycleOwner, {
                    sernameEdit.text = it
                })

            dataModel.userAvatar.observe(activity as LifecycleOwner, { uri ->
                if (userImage != null) {
                    uri?.let {
                        userImage.setImageURI(it)
                    } ?: run {
                        userImage.setImageResource(R.drawable.user)
                    }
                }
            })



        }
    }

    private fun vkWakeup() {
        with(binding) {
            vkNavigate.setOnClickListener() {
                val url = "https://vk.com/wakeup0002"

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }
    }

    private fun feedbackProfile() {

        binding.feedback.setOnClickListener() {
            NavigationFragment.NavigationFeedback(MAIN.navController)
        }

    }


}